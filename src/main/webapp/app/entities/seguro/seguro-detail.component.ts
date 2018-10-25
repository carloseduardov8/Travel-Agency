import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISeguro } from 'app/shared/model/seguro.model';

@Component({
    selector: 'jhi-seguro-detail',
    templateUrl: './seguro-detail.component.html'
})
export class SeguroDetailComponent implements OnInit {
    seguro: ISeguro;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ seguro }) => {
            this.seguro = seguro;
        });
    }

    previousState() {
        window.history.back();
    }
}
