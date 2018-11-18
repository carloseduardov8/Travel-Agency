import { Component, OnInit } from '@angular/core';
import { CompraService } from 'app/entities/compra';
import { HttpResponse } from '@angular/common/http';
import { NgxSpinnerService } from 'ngx-spinner';

import { ICompra } from 'app/shared/model/compra.model';
import { ContratoService } from 'app/entities/contrato';
import { LocacaoService } from 'app/entities/locacao';
import { ReservaService } from 'app/entities/reserva';
import { PassagemService } from 'app/entities/passagem';
import { ISeguro } from 'app/shared/model/seguro.model';
import { IContrato } from 'app/shared/model/contrato.model';
import { ILocacao } from 'app/shared/model/locacao.model';

@Component({
    selector: 'jhi-purchases',
    templateUrl: './purchases.component.html',
    styleUrls: ['purchases.css']
})
export class PurchasesComponent implements OnInit {
    withoutPurchases = false;
    compras = [];
    seguros: ISeguro[];

    constructor(
        private compraService: CompraService,
        private contratoService: ContratoService,
        private locacaoService: LocacaoService,
        private reservaService: ReservaService,
        private passagemService: PassagemService,
        private spinner: NgxSpinnerService
    ) {}

    ngOnInit() {
        console.log(this.compraService.getComprasByUser());
        this.spinner.show();
        this.compraService.getComprasByUser().subscribe((res: HttpResponse<ICompra[]>) => {
            this.compras = res.body;
            if (this.compras.length === 0) {
                this.withoutPurchases = true;
            } else {
                for (let compra of this.compras) {
                    compra.total = 0;
                    // Obtem os contratos de seguro referentes a compra
                    this.contratoService.getContratosByCompraId(compra.id).subscribe((res: HttpResponse<IContrato[]>) => {
                        console.log('OK');
                        console.log(res);
                        compra.contratos = res.body;
                        compra.contratos.forEach(contrato => (compra.total += contrato.valor));
                    });
                    // Obtem as locacoes de veiculos referentes a compra
                    this.locacaoService.getLocacoesByCompraId(compra.id).subscribe((res: HttpResponse<ILocacao[]>) => {
                        console.log('OK');
                        console.log(res);
                        compra.locacoes = res.body;
                        compra.locacoes.forEach(locacao => (compra.total += locacao.valor));
                    });
                    // Obtem os passagesn referentes a compra
                    this.passagemService.getPassagemsByCompraId(compra.id).subscribe((res: HttpResponse<IContrato[]>) => {
                        console.log('OK');
                        console.log(res);
                        compra.passagens = res.body;
                        compra.passagens.forEach(passagem => (compra.total += passagem.voo.valor));
                    });
                    // Obtem as reservas de hoteis referentes a compra
                    this.reservaService.getReservasByCompraId(compra.id).subscribe((res: HttpResponse<IContrato[]>) => {
                        console.log('OK');
                        console.log(res);
                        compra.reservas = res.body;
                        compra.reservas.forEach(reserva => (compra.total += reserva.valor));
                        setTimeout(() => {
                            /** spinner ends after 1 seconds */
                            this.spinner.hide();
                        }, 2000);
                    });
                }
            }

            console.log('Compras:');
            console.log(this.compras);
        });
    }

    verCompra(id: number) {
        // this.locacaoService.getLocacoesByCompraId(id);
        // this.reservaService.getReservasByCompraId(id);
        // this.passagemService.getPassagemsByCompraId(id);
    }
}
