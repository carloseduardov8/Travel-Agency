import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { Router } from '@angular/router';

import { DatePipe } from '@angular/common';
import { LoginModalService, Principal, Account } from 'app/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

declare var $: any;

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.css']
})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;
    cidades = [];
    registerForm: FormGroup;
    submitted = false;
    dateInDefault: String;

    private jhiAlertService: JhiAlertService;

    constructor(
        private router: Router,
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private formBuilder: FormBuilder,
        private datePipe: DatePipe
    ) {}

    ngOnInit() {
        // DatePicker inicializacao
        $('#datepicker-start').datepicker({
            showAnim: 'drop',
            dateFormat: 'dd-mm-yy',
            minDate: new Date()
        });
        this.dateInDefault = this.datePipe.transform(new Date(), 'dd-MM-yyyy');

        $('#datepicker-end').datepicker({
            dateFormat: 'dd-mm-yy',
            minDate: new Date()
        });

        this.principal.identity().then(account => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();

        // Carrega todas as cidades no select

        this.registerForm = this.formBuilder.group({
            from: ['', Validators.required],
            to: ['', Validators.required]
        });
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', message => {
            this.principal.identity().then(account => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    // Carrega todas as cidades no select
    loadAll() {}

    onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    // Acesso conveninente aos campos do formulario
    get f() {
        return this.registerForm.controls;
    }

    onSubmit(from, to, dateIn, dateOut, passengers) {
        this.submitted = true;

        // Verifica se cidade de ida e volta são as mesmas
        if (from.value === to.value) {
            this.registerForm.controls.to.setErrors({ required: true });
            return;
        }

        if (this.registerForm.invalid) {
            return;
        }

        console.log(from.value);
        console.log(to.value);
        console.log(dateIn.value);
        console.log(dateOut.value);
        console.log(passengers.value);
        this.router.navigate([
            '/basket',
            {
                from: from.value,
                to: to.value,
                dateIn: dateIn.value,
                dateOut: dateOut.value,
                passengers: passengers.value
            }
        ]);
    }
}
