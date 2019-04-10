import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    ImageEnvoyeComponent,
    ImageEnvoyeDetailComponent,
    ImageEnvoyeUpdateComponent,
    ImageEnvoyeDeletePopupComponent,
    ImageEnvoyeDeleteDialogComponent,
    imageEnvoyeRoute,
    imageEnvoyePopupRoute
} from './';

const ENTITY_STATES = [...imageEnvoyeRoute, ...imageEnvoyePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ImageEnvoyeComponent,
        ImageEnvoyeDetailComponent,
        ImageEnvoyeUpdateComponent,
        ImageEnvoyeDeleteDialogComponent,
        ImageEnvoyeDeletePopupComponent
    ],
    entryComponents: [ImageEnvoyeComponent, ImageEnvoyeUpdateComponent, ImageEnvoyeDeleteDialogComponent, ImageEnvoyeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceImageEnvoyeModule {}
