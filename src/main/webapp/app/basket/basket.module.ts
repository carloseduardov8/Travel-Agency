import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ViajatoSharedModule } from '../shared';

import { ViajatoAppChoosePlaneModule } from '../choose-plane';

import { ViajatoAppChooseHotelModule } from '../choose-hotel';

import { BASKET_ROUTE, BasketComponent } from './';

@NgModule({
    imports: [ViajatoSharedModule, ViajatoAppChoosePlaneModule, ViajatoAppChooseHotelModule,
       RouterModule.forRoot(BASKET_ROUTE, { useHash: true })],
    declarations: [BasketComponent],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ViajatoAppBasketModule {}
