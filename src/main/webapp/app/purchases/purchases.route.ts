import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { PurchasesComponent } from './';

export const PURCHASES_ROUTE: Route = {
    path: 'purchases',
    component: PurchasesComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'purchases.title'
    },
    canActivate: [UserRouteAccessService]
};
