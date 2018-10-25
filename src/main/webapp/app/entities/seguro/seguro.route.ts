import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Seguro } from 'app/shared/model/seguro.model';
import { SeguroService } from './seguro.service';
import { SeguroComponent } from './seguro.component';
import { SeguroDetailComponent } from './seguro-detail.component';
import { SeguroUpdateComponent } from './seguro-update.component';
import { SeguroDeletePopupComponent } from './seguro-delete-dialog.component';
import { ISeguro } from 'app/shared/model/seguro.model';

@Injectable({ providedIn: 'root' })
export class SeguroResolve implements Resolve<ISeguro> {
    constructor(private service: SeguroService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((seguro: HttpResponse<Seguro>) => seguro.body));
        }
        return of(new Seguro());
    }
}

export const seguroRoute: Routes = [
    {
        path: 'seguro',
        component: SeguroComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'viajatoApp.seguro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'seguro/:id/view',
        component: SeguroDetailComponent,
        resolve: {
            seguro: SeguroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'viajatoApp.seguro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'seguro/new',
        component: SeguroUpdateComponent,
        resolve: {
            seguro: SeguroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'viajatoApp.seguro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'seguro/:id/edit',
        component: SeguroUpdateComponent,
        resolve: {
            seguro: SeguroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'viajatoApp.seguro.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const seguroPopupRoute: Routes = [
    {
        path: 'seguro/:id/delete',
        component: SeguroDeletePopupComponent,
        resolve: {
            seguro: SeguroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'viajatoApp.seguro.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
