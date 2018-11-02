import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { ChooseHotelComponent } from './';

export const CHOOSE_HOTEL_ROUTE: Route = {
    path: 'choose-hotel',
    component: ChooseHotelComponent,
    data: {
        authorities: [],
        pageTitle: 'choose-hotel.title'
    },
    canActivate: [UserRouteAccessService]
};
