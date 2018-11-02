import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { ChooseVehicleComponent } from './';

export const CHOOSE_VEHICLE_ROUTE: Route = {
    path: 'choose-vehicle',
    component: ChooseVehicleComponent,
    data: {
        authorities: [],
        pageTitle: 'choose-vehicle.title'
    },
    canActivate: [UserRouteAccessService]
};
