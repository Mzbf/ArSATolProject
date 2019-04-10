import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    ImageMaladieComponent,
    ImageMaladieDetailComponent,
    ImageMaladieUpdateComponent,
    ImageMaladieDeletePopupComponent,
    ImageMaladieDeleteDialogComponent,
    imageMaladieRoute,
    imageMaladiePopupRoute
} from './';

const ENTITY_STATES = [...imageMaladieRoute, ...imageMaladiePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ImageMaladieComponent,
        ImageMaladieDetailComponent,
        ImageMaladieUpdateComponent,
        ImageMaladieDeleteDialogComponent,
        ImageMaladieDeletePopupComponent
    ],
    entryComponents: [
        ImageMaladieComponent,
        ImageMaladieUpdateComponent,
        ImageMaladieDeleteDialogComponent,
        ImageMaladieDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceImageMaladieModule {}
