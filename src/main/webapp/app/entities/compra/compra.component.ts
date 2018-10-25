import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICompra } from 'app/shared/model/compra.model';
import { Principal } from 'app/core';
import { CompraService } from './compra.service';

@Component({
    selector: 'jhi-compra',
    templateUrl: './compra.component.html'
})
export class CompraComponent implements OnInit, OnDestroy {
    compras: ICompra[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private compraService: CompraService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.compraService.query().subscribe(
            (res: HttpResponse<ICompra[]>) => {
                this.compras = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCompras();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICompra) {
        return item.id;
    }

    registerChangeInCompras() {
        this.eventSubscriber = this.eventManager.subscribe('compraListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
