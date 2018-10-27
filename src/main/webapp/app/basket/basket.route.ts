import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { BasketComponent } from './';

export const BASKET_ROUTE: Routes = [
    {
        path: 'basket',
        component: BasketComponent,
        data: {
            authorities: [],
            pageTitle: 'basket.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'basket:query',
        component: BasketComponent,
        data: {
            authorities: [],
            pageTitle: 'basket.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
