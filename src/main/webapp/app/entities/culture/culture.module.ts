import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    CultureComponent,
    CultureDetailComponent,
    CultureUpdateComponent,
    CultureDeletePopupComponent,
    CultureDeleteDialogComponent,
    cultureRoute,
    culturePopupRoute
} from './';

const ENTITY_STATES = [...cultureRoute, ...culturePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CultureComponent,
        CultureDetailComponent,
        CultureUpdateComponent,
        CultureDeleteDialogComponent,
        CultureDeletePopupComponent
    ],
    entryComponents: [CultureComponent, CultureUpdateComponent, CultureDeleteDialogComponent, CultureDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceCultureModule {}
