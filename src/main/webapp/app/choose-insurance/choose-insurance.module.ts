import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ViajatoSharedModule } from '../shared';

import { CHOOSE_INSURANCE_ROUTE, ChooseInsuranceComponent } from './';

@NgModule({
    imports: [ViajatoSharedModule, RouterModule.forRoot([CHOOSE_INSURANCE_ROUTE], { useHash: true })],
    declarations: [ChooseInsuranceComponent],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
	exports: [ChooseInsuranceComponent]
})
export class ViajatoAppChooseInsuranceModule {}
