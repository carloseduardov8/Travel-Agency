import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { PaymentComponent } from './';

export const PAYMENT_ROUTE: Route = {
    path: 'payment',
    component: PaymentComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'payment.title'
    },
    canActivate: [UserRouteAccessService]
};
