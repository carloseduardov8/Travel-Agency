import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { IVoo } from 'app/shared/model/voo.model';
import { VeiculoService } from 'app/entities/veiculo';
import { IVeiculo } from 'app/shared/model/veiculo.model';
import { Location } from '@angular/common';

@Component({
    selector: 'jhi-choose-vehicle',
    templateUrl: './choose-vehicle.component.html',
    styleUrls: ['choose-vehicle.css']
})
export class ChooseVehicleComponent implements OnInit {
    veiculos: IVeiculo[];
    withoutVeiculos: boolean;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private veiculoService: VeiculoService, // private jhiAlertService: JhiAlertService,
        private location: Location
    ) {
        this.route.params.subscribe(params => {
            console.log(params);
            if (params) {
                if (params.from && params.to && params.dateIn) {
                    console.log('Passengers are ' + params.passengers);
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

    // Funcao para voltar ao componente anterior
    back() {
        this.location.back();
    }
}
