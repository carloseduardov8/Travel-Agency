import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { VooService } from 'app/entities/voo';
import { JhiAlertService } from 'ng-jhipster';
import { HttpResponse } from '@angular/common/http';
import { IVoo } from 'app/shared/model/voo.model';
import { SeguroService } from 'app/entities/seguro';
import { ISeguro, Seguro } from 'app/shared/model/seguro.model';
import { Location } from '@angular/common';
import { Contrato } from 'app/shared/model/contrato.model';
import * as moment from 'moment';
import _date = moment.unitOfTime._date;
import { BasketService } from 'app/basket/basket.service';

@Component({
    selector: 'jhi-choose-insurance',
    templateUrl: './choose-insurance.component.html',
    styleUrls: ['choose-insurance.css']
})
export class ChooseInsuranceComponent implements OnInit {
    message: string;
    seguros: ISeguro[];
    withoutSeguros: boolean;
    passengers = 1;
    diffDays = 1;
    dateIn: Date;
    dateOut: Date;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private seguroService: SeguroService,
        private jhiAlertService: JhiAlertService,
        private location: Location,
        private basketService: BasketService
    ) {
        this.route.params.subscribe(params => {
            console.log(params);
            if (params) {
                if (params.from && params.to && params.dateIn) {
                    console.log('Passengers are ' + params.passengers);
                    this.passengers = params.passengers;

                    // Preco calculado pelo preco * numero de passageiros * num de dias
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

                    this.seguroService.findSeguros(params.to).subscribe((res: HttpResponse<IVoo[]>) => {
                        this.seguros = res.body;
                        if (this.seguros.length === 0) {
                            this.withoutSeguros = true;
                        }
                    });
                }
            }
        });
    }

    ngOnInit() {}

    // Comprar seguro
    buy(insurance) {
        console.log(insurance);
        let contrato = new Contrato();
        contrato.seguro = insurance;
        contrato.dataInicio =
            ('0' + this.dateIn.getDate()).slice(-2) + '-' + (this.dateIn.getMonth() + 1) + '-' + this.dateIn.getFullYear() + ' 00:00:00';

        contrato.dataFim =
            ('0' + this.dateOut.getDate()).slice(-2) + '-' + (this.dateOut.getMonth() + 1) + '-' + this.dateOut.getFullYear() + ' 00:00:00';

        contrato.numPessoas = this.passengers;
        contrato.compra = this.basketService.compra;

        console.log(contrato);
    }

    // Funcao para voltar ao componente anterior
    back() {
        this.location.back();
    }
}
