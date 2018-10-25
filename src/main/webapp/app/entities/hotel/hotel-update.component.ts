import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IHotel } from 'app/shared/model/hotel.model';
import { HotelService } from './hotel.service';

@Component({
    selector: 'jhi-hotel-update',
    templateUrl: './hotel-update.component.html'
})
export class HotelUpdateComponent implements OnInit {
    private _hotel: IHotel;
    isSaving: boolean;

    constructor(private hotelService: HotelService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ hotel }) => {
            this.hotel = hotel;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.hotel.id !== undefined) {
            this.subscribeToSaveResponse(this.hotelService.update(this.hotel));
        } else {
            this.subscribeToSaveResponse(this.hotelService.create(this.hotel));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IHotel>>) {
        result.subscribe((res: HttpResponse<IHotel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get hotel() {
        return this._hotel;
    }

    set hotel(hotel: IHotel) {
        this._hotel = hotel;
    }
}
