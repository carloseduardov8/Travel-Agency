import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISeguro } from 'app/shared/model/seguro.model';
import { SeguroService } from './seguro.service';
import { ISeguradora } from 'app/shared/model/seguradora.model';
import { SeguradoraService } from 'app/entities/seguradora';

@Component({
    selector: 'jhi-seguro-update',
    templateUrl: './seguro-update.component.html'
})
export class SeguroUpdateComponent implements OnInit {
    private _seguro: ISeguro;
    isSaving: boolean;

    seguradoras: ISeguradora[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private seguroService: SeguroService,
        private seguradoraService: SeguradoraService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ seguro }) => {
            this.seguro = seguro;
        });
        this.seguradoraService.query().subscribe(
            (res: HttpResponse<ISeguradora[]>) => {
                this.seguradoras = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.seguro.id !== undefined) {
            this.subscribeToSaveResponse(this.seguroService.update(this.seguro));
        } else {
            this.subscribeToSaveResponse(this.seguroService.create(this.seguro));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISeguro>>) {
        result.subscribe((res: HttpResponse<ISeguro>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackSeguradoraById(index: number, item: ISeguradora) {
        return item.id;
    }
    get seguro() {
        return this._seguro;
    }

    set seguro(seguro: ISeguro) {
        this._seguro = seguro;
    }
}
