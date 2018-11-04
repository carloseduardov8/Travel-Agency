import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ViajatoSharedModule } from '../shared';

import { CHOOSE_PLANE_ROUTE, ChoosePlaneComponent } from './';

@NgModule({
    imports: [ViajatoSharedModule, RouterModule.forRoot([CHOOSE_PLANE_ROUTE], { useHash: true })],
    declarations: [ChoosePlaneComponent],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ViajatoAppChoosePlaneModule {}
