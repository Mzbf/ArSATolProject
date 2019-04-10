import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    TypeDegatComponent,
    TypeDegatDetailComponent,
    TypeDegatUpdateComponent,
    TypeDegatDeletePopupComponent,
    TypeDegatDeleteDialogComponent,
    typeDegatRoute,
    typeDegatPopupRoute
} from './';

const ENTITY_STATES = [...typeDegatRoute, ...typeDegatPopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TypeDegatComponent,
        TypeDegatDetailComponent,
        TypeDegatUpdateComponent,
        TypeDegatDeleteDialogComponent,
        TypeDegatDeletePopupComponent
    ],
    entryComponents: [TypeDegatComponent, TypeDegatUpdateComponent, TypeDegatDeleteDialogComponent, TypeDegatDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceTypeDegatModule {}
