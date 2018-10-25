import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IContrato } from 'app/shared/model/contrato.model';
import { ContratoService } from './contrato.service';
import { ICompra } from 'app/shared/model/compra.model';
import { CompraService } from 'app/entities/compra';
import { ISeguro } from 'app/shared/model/seguro.model';
import { SeguroService } from 'app/entities/seguro';

@Component({
    selector: 'jhi-contrato-update',
    templateUrl: './contrato-update.component.html'
})
export class ContratoUpdateComponent implements OnInit {
    private _contrato: IContrato;
    isSaving: boolean;

    compras: ICompra[];

    seguros: ISeguro[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private contratoService: ContratoService,
        private compraService: CompraService,
        private seguroService: SeguroService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ contrato }) => {
            this.contrato = contrato;
        });
        this.compraService.query().subscribe(
            (res: HttpResponse<ICompra[]>) => {
                this.compras = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.seguroService.query().subscribe(
            (res: HttpResponse<ISeguro[]>) => {
                this.seguros = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.contrato.id !== undefined) {
            this.subscribeToSaveResponse(this.contratoService.update(this.contrato));
        } else {
            this.subscribeToSaveResponse(this.contratoService.create(this.contrato));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IContrato>>) {
        result.subscribe((res: HttpResponse<IContrato>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCompraById(index: number, item: ICompra) {
        return item.id;
    }

    trackSeguroById(index: number, item: ISeguro) {
        return item.id;
    }
    get contrato() {
        return this._contrato;
    }

    set contrato(contrato: IContrato) {
        this._contrato = contrato;
    }
}
