import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISeguro } from 'app/shared/model/seguro.model';
import { Principal } from 'app/core';
import { SeguroService } from './seguro.service';

@Component({
    selector: 'jhi-seguro',
    templateUrl: './seguro.component.html'
})
export class SeguroComponent implements OnInit, OnDestroy {
    seguros: ISeguro[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private seguroService: SeguroService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.seguroService.query().subscribe(
            (res: HttpResponse<ISeguro[]>) => {
                this.seguros = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSeguros();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISeguro) {
        return item.id;
    }

    registerChangeInSeguros() {
        this.eventSubscriber = this.eventManager.subscribe('seguroListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
