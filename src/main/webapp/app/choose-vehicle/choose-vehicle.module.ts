import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ViajatoSharedModule } from '../shared';

import { CHOOSE_VEHICLE_ROUTE, ChooseVehicleComponent } from './';

@NgModule({
    imports: [ViajatoSharedModule, RouterModule.forRoot([CHOOSE_VEHICLE_ROUTE], { useHash: true })],
    declarations: [ChooseVehicleComponent],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ViajatoAppChooseVehicleModule {}
