import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    AttaqueComponent,
    AttaqueDetailComponent,
    AttaqueUpdateComponent,
    AttaqueDeletePopupComponent,
    AttaqueDeleteDialogComponent,
    attaqueRoute,
    attaquePopupRoute
} from './';

const ENTITY_STATES = [...attaqueRoute, ...attaquePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AttaqueComponent,
        AttaqueDetailComponent,
        AttaqueUpdateComponent,
        AttaqueDeleteDialogComponent,
        AttaqueDeletePopupComponent
    ],
    entryComponents: [AttaqueComponent, AttaqueUpdateComponent, AttaqueDeleteDialogComponent, AttaqueDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceAttaqueModule {}
