import { Component, OnInit } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { ActivatedRoute, Router } from '@angular/router';
import { VooService } from 'app/entities/voo';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { IVoo } from 'app/shared/model/voo.model';
import { JhiAlertService } from 'ng-jhipster';
import { Passagem } from 'app/shared/model/passagem.model';
import { Location } from '@angular/common';

declare var $: any;

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
    seats: any[][][];
    columns: any[];
    flip: string = 'inactive';
    rows: any[];
    voos: any[] = [];
    withoutVoos = false;
    passengers: number = 0;
    seatsSelected: any[];

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private vooService: VooService,
        private jhiAlertService: JhiAlertService,
        private location: Location
    ) {
        this.route.params.subscribe(params => {
            console.log(params);
            if (params) {
                if (params.from && params.to && params.dateIn) {
                    console.log('Passengers are ' + params.passengers);
                    this.passengers = params.passengers;
                    console.log(
                        this.vooService.findVoos(params.dateIn, params.from, params.to).subscribe(
                            (res: HttpResponse<IVoo[]>) => {
                                this.voos = res.body;
                                if (this.voos.length === 0) {
                                    this.withoutVoos = true;
                                }

                                // Assigns an increasing internal ID to each flight:
                                for (let i = 0; i < this.voos.length; i++) {
                                    this.voos[i].ith = i;
                                }

                                // Generates airplane seats for each flight:
                                this.seats = new Array();
                                this.seatsSelected = new Array();

                                const numOfSeats = 17;

                                // Plane rows:
                                this.rows = ['A', 'B', 'C', 'D'];
                                // Loops through flights:
                                for (let k = 0; k < this.voos.length; k++) {
                                    console.log('Creating seats for flight ' + k);
                                    this.seats[k] = new Array();
                                    // Loops through rows:
                                    for (let j = 0; j < this.rows.length; j++) {
                                        this.seats[k][j] = new Array();
                                        // Loops through seats:
                                        for (let i = 0; i < numOfSeats; i++) {
                                            let seat = {
                                                // Calculates a unique identifier for each seat:
                                                id: 'seat' + k * this.voos.length * numOfSeats + j * this.rows.length + i,
                                                // Seat name to be displayed on plane ticket:
                                                name: this.rows[j] + (numOfSeats - i),
                                                // Flight that this seat is associated with:
                                                flight: this.voos[k].numero,
                                                // Seat status (if selected by the user):
                                                checked: false
                                            };
                                            this.seats[k][j].push(seat);
                                        }
                                    }
                                }
                            },
                            (res: HttpErrorResponse) => this.onError(res.message)
                        )
                    );
                }
            }
        });

        this.message = 'ChoosePlaneComponent message';
    }

    ngOnInit() {}

    // Troca o estado de flip, acionando a animacao
    toggleFlip(voo) {
        voo.flip = voo.flip === 'inactive' || voo.flip === undefined ? 'active' : 'inactive';
    }

    selectSeat(seat) {
        // Checks state of seat:
        if (seat.checked == false) {
            seat.checked = true;
            this.seatsSelected.push(seat);
        } else {
            seat.checked = false;
            for (let i = 0; i < this.seatsSelected.length; i++) {
                if (this.seatsSelected[i].id === seat.id) {
                    this.seatsSelected.splice(i, 1);
                }
            }
        }
        // Sets the height of the confirmation window:
        $('.confirmation-window').height(110 + this.seatsSelected.length * 31);
    }

    selectVoos() {
        //let passagem = new Passagem()
        //passagem.assento
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    // Funcao para voltar ao componente anterior
    back() {
        this.location.back();
    }
}
