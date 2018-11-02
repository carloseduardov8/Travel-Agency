import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { ChooseInsuranceComponent } from './';

export const CHOOSE_INSURANCE_ROUTE: Route = {
    path: 'choose-insurance',
    component: ChooseInsuranceComponent,
    data: {
        authorities: [],
        pageTitle: 'choose-insurance.title'
    },
    canActivate: [UserRouteAccessService]
};
