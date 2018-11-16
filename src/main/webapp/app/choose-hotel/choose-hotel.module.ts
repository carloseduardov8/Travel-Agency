import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ViajatoSharedModule } from '../shared';

import { CHOOSE_HOTEL_ROUTE, ChooseHotelComponent } from './';

@NgModule({
    imports: [ViajatoSharedModule, RouterModule.forRoot([CHOOSE_HOTEL_ROUTE], { useHash: true })],
    declarations: [ChooseHotelComponent],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [ChooseHotelComponent]
})
export class ViajatoAppChooseHotelModule {}
