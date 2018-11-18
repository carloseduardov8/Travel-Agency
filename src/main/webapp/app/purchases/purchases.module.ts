import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ViajatoSharedModule } from '../shared';

import { PURCHASES_ROUTE, PurchasesComponent } from './';
import { NgxSpinnerModule } from 'ngx-spinner';

@NgModule({
    imports: [ViajatoSharedModule, RouterModule.forRoot([PURCHASES_ROUTE], { useHash: true }), NgxSpinnerModule],
    declarations: [PurchasesComponent],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ViajatoAppPurchasesModule {}
