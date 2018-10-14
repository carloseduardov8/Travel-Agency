import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'jhi-basket',
    templateUrl: './basket.component.html',
    styleUrls: ['basket.css']
})
export class BasketComponent implements OnInit {
    message: string;

    constructor(private route: ActivatedRoute, private router: Router) {
        this.route.params.subscribe(params => {
            console.log(params);
            if (params['query']) {
                this.doSearch(params['query']);
            }
        });
    }

    ngOnInit() {}

    doSearch(query) {
        console.log(query);
    }
}
