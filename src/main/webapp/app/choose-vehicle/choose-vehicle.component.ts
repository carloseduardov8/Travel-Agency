import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { IVoo } from 'app/shared/model/voo.model';
import { VeiculoService } from 'app/entities/veiculo';
import { IVeiculo } from 'app/shared/model/veiculo.model';
import { Location } from '@angular/common';
import { NgxSpinnerService } from 'ngx-spinner';
import { Contrato } from 'app/shared/model/contrato.model';
import { Locacao } from 'app/shared/model/locacao.model';
import { BasketService } from 'app/basket/basket.service';

@Component({
    selector: 'jhi-choose-vehicle',
    templateUrl: './choose-vehicle.component.html',
    styleUrls: ['choose-vehicle.css']
})
export class ChooseVehicleComponent implements OnInit {
    veiculos: IVeiculo[];
    withoutVeiculos: boolean;
    dateIn: Date;
    dateOut: Date;
    passengers: number;
    diffDays = 1;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private veiculoService: VeiculoService, // private jhiAlertService: JhiAlertService,
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

                    this.veiculoService.findVeiculos(params.to).subscribe((res: HttpResponse<IVoo[]>) => {
                        this.veiculos = res.body;
                        console.log(this.veiculos);
                        if (this.veiculos.length === 0) {
                            this.withoutVeiculos = true;
                        }
                    });
                }
            }
        });
    }

    ngOnInit() {}

    // Alugar Veiculo
    rent(vehicle) {
        console.log(vehicle);
        let locacao = new Locacao();
        locacao.valor = vehicle.valor * this.diffDays;
        locacao.veiculo = vehicle;
        locacao.dataInicio = ('0' + this.dateIn.getDate()).slice(-2) + '-' + (this.dateIn.getMonth() + 1) + '-' + this.dateIn.getFullYear();
        locacao.dataFim = ('0' + this.dateOut.getDate()).slice(-2) + '-' + (this.dateOut.getMonth() + 1) + '-' + this.dateOut.getFullYear();

        // Checa se locacao ja existe na cesta:
        if (!this.basketService.containsVehicle(locacao.veiculo)) {
            // Adiciona locacao a cesta
            this.basketService.locacoes.push(locacao);
        }

        // Sets the height of the confirmation window:
        this.basketService.resizeConfirmationWindow();
    }

    // Funcao para voltar ao componente anterior
    back() {
        this.location.back();
    }
}
