import { Component, OnInit } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { ActivatedRoute, Router } from '@angular/router';
import { VooService } from 'app/entities/voo';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { IVoo } from 'app/shared/model/voo.model';
import { JhiAlertService } from 'ng-jhipster';

@Component({
    selector: 'jhi-choose-plane',
    templateUrl: './choose-plane.component.html',
    styleUrls: ['choose-plane.css'],
    animations: [
        trigger('flipState', [
            state(
                'active',
                style({
                    transform: 'rotateY(179.9deg)'
                })
            ),
            state(
                'inactive',
                style({
                    transform: 'rotateY(0)'
                })
            ),
            transition('active => inactive', animate('500ms ease-out')),
            transition('inactive => active', animate('500ms ease-in'))
        ])
    ]
})
export class ChoosePlaneComponent implements OnInit {
    message: string;
    flip = 'inactive';
    seats: any[][];
    columns: any[];
    rows: any[];
    voos: any[] = [];
    withoutVoos = false;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private vooService: VooService,
        private jhiAlertService: JhiAlertService
    ) {
        this.route.params.subscribe(params => {
            console.log(params);
            if (params) {
                if (params.from && params.to && params.dateIn) {
                    console.log(
                        this.vooService.findVoos(params.dateIn, params.from, params.to).subscribe(
                            (res: HttpResponse<IVoo[]>) => {
                                this.voos = res.body;
                                if (this.voos.length === 0) {
                                    this.withoutVoos = true;
                                }
                            },
                            (res: HttpErrorResponse) => this.onError(res.message)
                        )
                    );
                }
            }
        });

        this.message = 'ChoosePlaneComponent message';
        this.seats = new Array();

        const numOfSeats = 17;

        // Plane rows:
        this.rows = ['A', 'B', 'C', 'D'];
        // Loops through rows:
        for (let j = 0; j < 4; j++) {
            this.seats[j] = new Array();
            // Loops through seats:
            for (let i = 0; i < numOfSeats; i++) {
                this.seats[j].push(this.rows[j] + (numOfSeats - i));
            }
        }
    }

    ngOnInit() {}

    // Troca o estado de flip, acionando a animacao
    toggleFlip() {
        this.flip = this.flip === 'inactive' ? 'active' : 'inactive';
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
