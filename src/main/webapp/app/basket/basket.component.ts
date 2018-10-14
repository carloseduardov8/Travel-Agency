import { Component, OnInit } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'jhi-basket',
    templateUrl: './basket.component.html',
    styleUrls: ['basket.css'],
    animations: [
        trigger('flipState', [
            state(
                'active',
                style({
                    transform: 'rotateY(179.9deg)'
                })
            ),
            state(
                'inactive',
                style({
                    transform: 'rotateY(0)'
                })
            ),
            transition('active => inactive', animate('500ms ease-out')),
            transition('inactive => active', animate('500ms ease-in'))
        ])
    ]
})
export class BasketComponent implements OnInit {
    message: string;
    flip = 'inactive';

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

    // Troca o estado de flip, acionando a animacao
    toggleFlip() {
        this.flip = this.flip === 'inactive' ? 'active' : 'inactive';
    }
}
