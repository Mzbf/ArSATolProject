import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    TypeCultureComponent,
    TypeCultureDetailComponent,
    TypeCultureUpdateComponent,
    TypeCultureDeletePopupComponent,
    TypeCultureDeleteDialogComponent,
    typeCultureRoute,
    typeCulturePopupRoute
} from './';

const ENTITY_STATES = [...typeCultureRoute, ...typeCulturePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TypeCultureComponent,
        TypeCultureDetailComponent,
        TypeCultureUpdateComponent,
        TypeCultureDeleteDialogComponent,
        TypeCultureDeletePopupComponent
    ],
    entryComponents: [TypeCultureComponent, TypeCultureUpdateComponent, TypeCultureDeleteDialogComponent, TypeCultureDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceTypeCultureModule {}
