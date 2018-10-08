import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { ChoosePlaneComponent } from './';

export const CHOOSE_PLANE_ROUTE: Route = {
  path: 'choose-plane',
  component: ChoosePlaneComponent,
  data: {
    authorities: [],
    pageTitle: 'choose-plane.title'
  },
  canActivate: [UserRouteAccessService]
};
