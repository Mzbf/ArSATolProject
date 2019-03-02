import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    AdminInsecteComponent,
    AdminInsecteDetailComponent,
    AdminInsecteUpdateComponent,
    AdminInsecteDeletePopupComponent,
    AdminInsecteDeleteDialogComponent,
    adminInsecteRoute,
    adminInsectePopupRoute
} from './';

const ENTITY_STATES = [...adminInsecteRoute, ...adminInsectePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AdminInsecteComponent,
        AdminInsecteDetailComponent,
        AdminInsecteUpdateComponent,
        AdminInsecteDeleteDialogComponent,
        AdminInsecteDeletePopupComponent
    ],
    entryComponents: [
        AdminInsecteComponent,
        AdminInsecteUpdateComponent,
        AdminInsecteDeleteDialogComponent,
        AdminInsecteDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceAdminInsecteModule {}
