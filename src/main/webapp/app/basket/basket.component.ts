import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { VooService } from 'app/entities/voo';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { IVoo } from 'app/shared/model/voo.model';

@Component({
    selector: 'jhi-basket',
    templateUrl: './basket.component.html',
    styleUrls: ['basket.css']
})
export class BasketComponent implements OnInit {
    from: string;
    to: string;
    dateIn: string;
    dateOut: string;
    passengers: string;

    constructor(private route: ActivatedRoute, private router: Router, private vooService: VooService) {
        this.route.params.subscribe(params => {
            console.log(params);
            if (params) {
                this.from = params.from;
                this.to = params.to;
                this.dateIn = params.dateIn;
                this.dateOut = params.dateOut;
                this.passengers = params.passengers;
            }
        });
    }

    ngOnInit() {}

    // Ir para uma das páginas de selecao com os parametros selecionados
    goTo(page: string) {
        this.router.navigate([
            '/' + page,
            {
                from: this.from,
                to: this.to,
                dateIn: this.dateIn,
                dateOut: this.dateOut,
                passengers: this.passengers
            }
        ]);
    }
}
