import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    ImageHerbeComponent,
    ImageHerbeDetailComponent,
    ImageHerbeUpdateComponent,
    ImageHerbeDeletePopupComponent,
    ImageHerbeDeleteDialogComponent,
    imageHerbeRoute,
    imageHerbePopupRoute
} from './';

const ENTITY_STATES = [...imageHerbeRoute, ...imageHerbePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ImageHerbeComponent,
        ImageHerbeDetailComponent,
        ImageHerbeUpdateComponent,
        ImageHerbeDeleteDialogComponent,
        ImageHerbeDeletePopupComponent
    ],
    entryComponents: [ImageHerbeComponent, ImageHerbeUpdateComponent, ImageHerbeDeleteDialogComponent, ImageHerbeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceImageHerbeModule {}
