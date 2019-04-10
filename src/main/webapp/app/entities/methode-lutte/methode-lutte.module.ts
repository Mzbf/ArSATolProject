import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    MethodeLutteComponent,
    MethodeLutteDetailComponent,
    MethodeLutteUpdateComponent,
    MethodeLutteDeletePopupComponent,
    MethodeLutteDeleteDialogComponent,
    methodeLutteRoute,
    methodeLuttePopupRoute
} from './';

const ENTITY_STATES = [...methodeLutteRoute, ...methodeLuttePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MethodeLutteComponent,
        MethodeLutteDetailComponent,
        MethodeLutteUpdateComponent,
        MethodeLutteDeleteDialogComponent,
        MethodeLutteDeletePopupComponent
    ],
    entryComponents: [
        MethodeLutteComponent,
        MethodeLutteUpdateComponent,
        MethodeLutteDeleteDialogComponent,
        MethodeLutteDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceMethodeLutteModule {}
