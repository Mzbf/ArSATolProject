import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    SuperFamilleComponent,
    SuperFamilleDetailComponent,
    SuperFamilleUpdateComponent,
    SuperFamilleDeletePopupComponent,
    SuperFamilleDeleteDialogComponent,
    superFamilleRoute,
    superFamillePopupRoute
} from './';

const ENTITY_STATES = [...superFamilleRoute, ...superFamillePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SuperFamilleComponent,
        SuperFamilleDetailComponent,
        SuperFamilleUpdateComponent,
        SuperFamilleDeleteDialogComponent,
        SuperFamilleDeletePopupComponent
    ],
    entryComponents: [
        SuperFamilleComponent,
        SuperFamilleUpdateComponent,
        SuperFamilleDeleteDialogComponent,
        SuperFamilleDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceSuperFamilleModule {}
