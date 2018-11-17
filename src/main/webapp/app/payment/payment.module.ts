import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ViajatoSharedModule } from '../shared';

import { PAYMENT_ROUTE, PaymentComponent } from './';

@NgModule({
    imports: [ViajatoSharedModule, RouterModule.forRoot([PAYMENT_ROUTE], { useHash: true })],
    declarations: [PaymentComponent],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [PaymentComponent]
})
export class ViajatoAppPaymentModule {}
