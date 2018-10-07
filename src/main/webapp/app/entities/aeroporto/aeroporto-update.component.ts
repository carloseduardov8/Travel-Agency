import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAeroporto } from 'app/shared/model/aeroporto.model';
import { AeroportoService } from './aeroporto.service';

@Component({
    selector: 'jhi-aeroporto-update',
    templateUrl: './aeroporto-update.component.html'
})
export class AeroportoUpdateComponent implements OnInit {
    private _aeroporto: IAeroporto;
    isSaving: boolean;

    constructor(private aeroportoService: AeroportoService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ aeroporto }) => {
            this.aeroporto = aeroporto;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.aeroporto.id !== undefined) {
            this.subscribeToSaveResponse(this.aeroportoService.update(this.aeroporto));
        } else {
            this.subscribeToSaveResponse(this.aeroportoService.create(this.aeroporto));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAeroporto>>) {
        result.subscribe((res: HttpResponse<IAeroporto>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get aeroporto() {
        return this._aeroporto;
    }

    set aeroporto(aeroporto: IAeroporto) {
        this._aeroporto = aeroporto;
    }
}
