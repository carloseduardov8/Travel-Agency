import { Component, OnInit } from '@angular/core';
import { IPassagem } from 'app/shared/model/passagem.model';
import { ILocacao } from 'app/shared/model/locacao.model';
import { IReserva } from 'app/shared/model/reserva.model';
import { IContrato } from 'app/shared/model/contrato.model';
import { BasketService } from 'app/basket/basket.service';
import { Location } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { IUser, Principal, User, UserService } from 'app/core';

@Component({
    selector: 'jhi-payment',
    templateUrl: './payment.component.html',
    styleUrls: ['payment.css']
})
export class PaymentComponent implements OnInit {
    passagens = new Array<IPassagem>();
    locacoes = new Array<ILocacao>();
    reservas = new Array<IReserva>();
    contratos = new Array<IContrato>();
    user: IUser;
    total: number = 0;
    constructor(
        private basketService: BasketService,
        private location: Location,
        private router: Router,
        private principal: Principal,
        private userService: UserService,
        private route: ActivatedRoute
    ) {
        this.locacoes = this.basketService.locacoes;
        this.passagens = this.basketService.passagens;
        this.reservas = this.basketService.reservas;
        this.contratos = this.basketService.contratos;
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            let login = account.login;
            this.userService.find(login).subscribe(res => {
                this.user = res.body;
                console.log('user');
                console.log(this.user);
            });
        });

        this.calculateSum();
    }

    // Calcula a soma de tudo o que foi selecionado
    calculateSum() {
        this.total = 0;
        this.locacoes.forEach(locacao => (this.total += locacao.valor));
        this.contratos.forEach(contrato => (this.total += contrato.valor));
        this.passagens.forEach(passagem => (this.total += passagem.voo.valor));
        this.reservas.forEach(reserva => (this.total += reserva.valor));
    }

    delItem(items, item) {
        console.log(items);
        items = items.filter(obj => obj !== item);
        console.log(items);
    }

    confirmPayment() {
        this.basketService.savePayment(this.user);
        this.router.navigate(['/purchases', {}]);
    }

    // Funcao para voltar ao componente anterior
    back() {
        this.location.back();
    }
}
