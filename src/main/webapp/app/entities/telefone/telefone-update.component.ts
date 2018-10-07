import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ITelefone } from 'app/shared/model/telefone.model';
import { TelefoneService } from './telefone.service';

@Component({
    selector: 'jhi-telefone-update',
    templateUrl: './telefone-update.component.html'
})
export class TelefoneUpdateComponent implements OnInit {
    private _telefone: ITelefone;
    isSaving: boolean;

    constructor(private telefoneService: TelefoneService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ telefone }) => {
            this.telefone = telefone;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.telefone.id !== undefined) {
            this.subscribeToSaveResponse(this.telefoneService.update(this.telefone));
        } else {
            this.subscribeToSaveResponse(this.telefoneService.create(this.telefone));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITelefone>>) {
        result.subscribe((res: HttpResponse<ITelefone>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get telefone() {
        return this._telefone;
    }

    set telefone(telefone: ITelefone) {
        this._telefone = telefone;
    }
}
