import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    InsecteRavageurComponent,
    InsecteRavageurDetailComponent,
    InsecteRavageurUpdateComponent,
    InsecteRavageurDeletePopupComponent,
    InsecteRavageurDeleteDialogComponent,
    insecteRavageurRoute,
    insecteRavageurPopupRoute
} from './';

const ENTITY_STATES = [...insecteRavageurRoute, ...insecteRavageurPopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InsecteRavageurComponent,
        InsecteRavageurDetailComponent,
        InsecteRavageurUpdateComponent,
        InsecteRavageurDeleteDialogComponent,
        InsecteRavageurDeletePopupComponent
    ],
    entryComponents: [
        InsecteRavageurComponent,
        InsecteRavageurUpdateComponent,
        InsecteRavageurDeleteDialogComponent,
        InsecteRavageurDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceInsecteRavageurModule {}
