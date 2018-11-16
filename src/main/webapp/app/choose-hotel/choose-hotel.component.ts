import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
//import {JhiAlertService} from "ng-jhipster";
import { HttpResponse } from '@angular/common/http';
import { QuartoService } from 'app/entities/quarto';
import { IQuarto } from 'app/shared/model/quarto.model';
import { Location } from '@angular/common';

@Component({
    selector: 'jhi-choose-hotel',
    templateUrl: './choose-hotel.component.html',
    styleUrls: ['choose-hotel.css']
})
export class ChooseHotelComponent implements OnInit {
    quartos: IQuarto[];
    withoutQuartos: boolean;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private quartoService: QuartoService, //  private jhiAlertService: JhiAlertService
        private location: Location
    ) {
        this.route.params.subscribe(params => {
            console.log(params);
            if (params) {
                if (params.from && params.to && params.dateIn) {
                    console.log('Passengers are ' + params.passengers);
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

    ngOnInit() {}

    // Funcao para voltar ao componente anterior
    back() {
        this.location.back();
    }
}
