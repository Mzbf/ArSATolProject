import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    InsecteComponent,
    InsecteDetailComponent,
    InsecteUpdateComponent,
    InsecteDeletePopupComponent,
    InsecteDeleteDialogComponent,
    insecteRoute,
    insectePopupRoute
} from './';

const ENTITY_STATES = [...insecteRoute, ...insectePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InsecteComponent,
        InsecteDetailComponent,
        InsecteUpdateComponent,
        InsecteDeleteDialogComponent,
        InsecteDeletePopupComponent
    ],
    entryComponents: [InsecteComponent, InsecteUpdateComponent, InsecteDeleteDialogComponent, InsecteDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceInsecteModule {}
