import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
//import {JhiAlertService} from "ng-jhipster";
import { HttpResponse } from '@angular/common/http';
import { QuartoService } from 'app/entities/quarto';
import { IQuarto } from 'app/shared/model/quarto.model';
import { Location } from '@angular/common';
import { Contrato } from 'app/shared/model/contrato.model';
import { Hotel } from 'app/shared/model/hotel.model';
import { Reserva } from 'app/shared/model/reserva.model';
import { BasketService } from 'app/basket/basket.service';

@Component({
    selector: 'jhi-choose-hotel',
    templateUrl: './choose-hotel.component.html',
    styleUrls: ['choose-hotel.css']
})
export class ChooseHotelComponent implements OnInit {
    quartos: IQuarto[];
    withoutQuartos: boolean;
    dateIn: Date;
    dateOut: Date;
    passengers: number;
    diffDays = 1;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private quartoService: QuartoService, //  private jhiAlertService: JhiAlertService
        private location: Location,
        private basketService: BasketService
    ) {
        this.route.params.subscribe(params => {
            console.log(params);
            if (params) {
                if (params.from && params.to && params.dateIn) {
                    this.dateIn = params.dateIn;
                    this.dateOut = params.dateOut;
                    this.passengers = params.passengers;
                    console.log('Passengers are ' + params.passengers);

                    // Preco calculado pelo preco * num de dias
                    if (params.dateOut) {
                        this.dateIn = new Date(
                            params.dateIn
                                .split('-')
                                .reverse()
                                .join('/')
                        );
                        this.dateOut = new Date(
                            params.dateOut
                                .split('-')
                                .reverse()
                                .join('/')
                        );
                        let diff = Math.ceil(Math.abs(this.dateOut.getTime() - this.dateIn.getTime()) / (1000 * 3600 * 24));
                        console.log('Diff ' + diff);
                        this.diffDays = diff > 1 ? diff : this.diffDays;
                    } else {
                        this.dateIn = new Date(
                            params.dateIn
                                .split('-')
                                .reverse()
                                .join('/')
                        );
                        this.dateOut = this.dateIn;
                    }

                    this.quartoService.findQuartos(params.to).subscribe((res: HttpResponse<IQuarto[]>) => {
                        this.quartos = res.body;
                        console.log(this.quartos);
                        if (this.quartos.length === 0) {
                            this.withoutQuartos = true;
                        }
                    });
                }
            }
        });
    }

    // Reservar quarto
    bookRoom(quarto: IQuarto) {
        let reserva = new Reserva();
        reserva.dataInicio = ('0' + this.dateIn.getDate()).slice(-2) + '-' + (this.dateIn.getMonth() + 1) + '-' + this.dateIn.getFullYear();
        reserva.dataFim = ('0' + this.dateOut.getDate()).slice(-2) + '-' + (this.dateOut.getMonth() + 1) + '-' + this.dateOut.getFullYear();
        reserva.numPessoas = this.passengers;
        reserva.valor = quarto.diaria * this.diffDays;
        reserva.quarto = quarto;
        console.log(reserva);

        // Checa se reserva ja existe na cesta:
        if (!this.basketService.containsRoom(reserva.quarto)) {
            // Adiciona reserva a cesta
            this.basketService.reservas.push(reserva);
        }

        // Sets the height of the confirmation window:
        this.basketService.resizeConfirmationWindow();
    }

    ngOnInit() {}

    // Funcao para voltar ao componente anterior
    back() {
        this.location.back();
    }
}
