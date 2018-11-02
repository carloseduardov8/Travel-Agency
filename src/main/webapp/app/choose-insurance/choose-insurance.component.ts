import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { VooService } from 'app/entities/voo';
import { JhiAlertService } from 'ng-jhipster';
import { HttpResponse } from '@angular/common/http';
import { IVoo } from 'app/shared/model/voo.model';
import { SeguroService } from 'app/entities/seguro';
import { ISeguro, Seguro } from 'app/shared/model/seguro.model';

@Component({
    selector: 'jhi-choose-insurance',
    templateUrl: './choose-insurance.component.html',
    styleUrls: ['choose-insurance.css']
})
export class ChooseInsuranceComponent implements OnInit {
    message: string;
    seguros: ISeguro[];
    withoutSeguros: boolean;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private seguroService: SeguroService,
        private jhiAlertService: JhiAlertService
    ) {
        this.route.params.subscribe(params => {
            console.log(params);
            if (params) {
                if (params.from && params.to && params.dateIn) {
                    console.log('Passengers are ' + params.passengers);
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
}
