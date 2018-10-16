import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {VooService} from "app/entities/voo";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {IVoo} from "app/shared/model/voo.model";

@Component({
    selector: 'jhi-basket',
    templateUrl: './basket.component.html',
    styleUrls: ['basket.css']
})
export class BasketComponent implements OnInit {
    message: string;

    constructor(private route: ActivatedRoute, private router: Router, private vooService:VooService) {
        this.route.params.subscribe(params => {
            console.log(params);
            if (params['query']) {
                this.doSearch(params['query']);
            }
        });

        console.log("ok");

        console.log(this.vooService.findVoos("20-10-2018 15:00", "1", "2").subscribe(
            res => {
                console.log(res);
            },
            (res) => (console.log(res.message))
        ));
    }



    ngOnInit() {}

    doSearch(query) {
        console.log(query);
    }
}
