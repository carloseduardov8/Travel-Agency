import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { BasketService } from 'app/basket/basket.service';

declare var $: any;

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
    viewWindow: number;

    constructor(
        private basketService: BasketService,
        private route: ActivatedRoute,
        private router: Router,
        private spinner: NgxSpinnerService
    ) {
        this.route.params.subscribe(params => {
            console.log(params);
            this.viewWindow = 1;
            if (params) {
                this.from = params.from;
                this.to = params.to;
                this.dateIn = params.dateIn;
                this.dateOut = params.dateOut;
                this.passengers = params.passengers;
            }
        });
    }

    ngOnInit() {
        this.spinner.show();
        setTimeout(() => {
            /** spinner ends after 2 seconds */
            this.spinner.hide();
        }, 2000);
    }

    // Troca o estado da janela (alterna entre mostrar passagem, hotel, etc)
    toggleWindow(num) {
        if (this.viewWindow !== num) {
            const fadeTime = 500;
            $('.component-window').hide();
            $('.component-window').fadeIn(fadeTime);
            $('.title-wrapper').hide();
            $('.title-wrapper').fadeIn(fadeTime);
        }
        this.viewWindow = num;
    }

    // Redireciona o usuario para a proxima janela (passagem -> hotel -> seguro -> veiculo -> carrinho):
    goToNextWindow() {
        this.toggleWindow(this.viewWindow + 1);
    }

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
