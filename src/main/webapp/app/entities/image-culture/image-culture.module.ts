import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    ImageCultureComponent,
    ImageCultureDetailComponent,
    ImageCultureUpdateComponent,
    ImageCultureDeletePopupComponent,
    ImageCultureDeleteDialogComponent,
    imageCultureRoute,
    imageCulturePopupRoute
} from './';

const ENTITY_STATES = [...imageCultureRoute, ...imageCulturePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ImageCultureComponent,
        ImageCultureDetailComponent,
        ImageCultureUpdateComponent,
        ImageCultureDeleteDialogComponent,
        ImageCultureDeletePopupComponent
    ],
    entryComponents: [
        ImageCultureComponent,
        ImageCultureUpdateComponent,
        ImageCultureDeleteDialogComponent,
        ImageCultureDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceImageCultureModule {}
