import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    ImageAttaqueComponent,
    ImageAttaqueDetailComponent,
    ImageAttaqueUpdateComponent,
    ImageAttaqueDeletePopupComponent,
    ImageAttaqueDeleteDialogComponent,
    imageAttaqueRoute,
    imageAttaquePopupRoute
} from './';

const ENTITY_STATES = [...imageAttaqueRoute, ...imageAttaquePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ImageAttaqueComponent,
        ImageAttaqueDetailComponent,
        ImageAttaqueUpdateComponent,
        ImageAttaqueDeleteDialogComponent,
        ImageAttaqueDeletePopupComponent
    ],
    entryComponents: [
        ImageAttaqueComponent,
        ImageAttaqueUpdateComponent,
        ImageAttaqueDeleteDialogComponent,
        ImageAttaqueDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceImageAttaqueModule {}
