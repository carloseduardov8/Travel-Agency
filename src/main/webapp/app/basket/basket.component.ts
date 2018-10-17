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
    message: string;
    from: string;
    to: string;
    dateIn: string;
    dateOut: string;
    passangers: string;

    constructor(private route: ActivatedRoute, private router: Router, private vooService: VooService) {
        this.route.params.subscribe(params => {
            console.log(params);
            if (params) {
                this.from = params.from;
                this.to = params.to;
                this.dateIn = params.dateIn;
                this.dateOut = params.dateOut;
                this.passangers = params.passangers;
            }
        });

        console.log('ok');

        console.log(
            this.vooService.findVoos('20-10-2018', '1', '2').subscribe(
                res => {
                    console.log(res);
                },
                res => console.log(res.message)
            )
        );
    }

    ngOnInit() {}

    doSearch(query) {
        console.log(query);
    }

    goToChoosePlane() {
        this.router.navigate([
            '/choose-plane',
            {
                from: this.from,
                to: this.to,
                dateIn: this.dateIn,
                dateOut: this.dateOut,
                passangers: this.passangers
            }
        ]);
    }
}
