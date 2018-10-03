import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { BasketComponent } from './';

export const BASKET_ROUTE: Route = {
  path: 'basket',
  component: BasketComponent,
  data: {
    authorities: [],
    pageTitle: 'basket.title'
  },
  canActivate: [UserRouteAccessService]
};
