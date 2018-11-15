import { Component, OnInit } from '@angular/core';
import { CompraService } from 'app/entities/compra';
import { UserService } from 'app/core';
import { HttpResponse } from '@angular/common/http';
import { IVoo } from 'app/shared/model/voo.model';
import { ICompra } from 'app/shared/model/compra.model';

@Component({
    selector: 'jhi-purchases',
    templateUrl: './purchases.component.html',
    styleUrls: ['purchases.css']
})
export class PurchasesComponent implements OnInit {
    withoutPurchases = false;
    compras = [];

    constructor(private compraService: CompraService) {}

    ngOnInit() {
        console.log(this.compraService.getComprasByUser());
        this.compraService.getComprasByUser().subscribe((res: HttpResponse<ICompra[]>) => {
            this.compras = res.body;
            if (this.compras.length === 0) {
                this.withoutPurchases = true;
            }
            console.log('Compras:');
            console.log(this.compras);
        });
    }
}
