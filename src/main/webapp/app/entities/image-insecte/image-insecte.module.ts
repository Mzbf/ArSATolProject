import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    ImageInsecteComponent,
    ImageInsecteDetailComponent,
    ImageInsecteUpdateComponent,
    ImageInsecteDeletePopupComponent,
    ImageInsecteDeleteDialogComponent,
    imageInsecteRoute,
    imageInsectePopupRoute
} from './';

const ENTITY_STATES = [...imageInsecteRoute, ...imageInsectePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ImageInsecteComponent,
        ImageInsecteDetailComponent,
        ImageInsecteUpdateComponent,
        ImageInsecteDeleteDialogComponent,
        ImageInsecteDeletePopupComponent
    ],
    entryComponents: [
        ImageInsecteComponent,
        ImageInsecteUpdateComponent,
        ImageInsecteDeleteDialogComponent,
        ImageInsecteDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceImageInsecteModule {}
