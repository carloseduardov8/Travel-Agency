import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ViajatoSharedModule } from 'app/shared';
import { ViajatoAdminModule } from 'app/admin/admin.module';
import {
    CompraComponent,
    CompraDetailComponent,
    CompraUpdateComponent,
    CompraDeletePopupComponent,
    CompraDeleteDialogComponent,
    compraRoute,
    compraPopupRoute
} from './';

const ENTITY_STATES = [...compraRoute, ...compraPopupRoute];

@NgModule({
    imports: [ViajatoSharedModule, ViajatoAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [CompraComponent, CompraDetailComponent, CompraUpdateComponent, CompraDeleteDialogComponent, CompraDeletePopupComponent],
    entryComponents: [CompraComponent, CompraUpdateComponent, CompraDeleteDialogComponent, CompraDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ViajatoCompraModule {}
