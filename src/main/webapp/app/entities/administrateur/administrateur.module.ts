import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    AdministrateurComponent,
    AdministrateurDetailComponent,
    AdministrateurUpdateComponent,
    AdministrateurDeletePopupComponent,
    AdministrateurDeleteDialogComponent,
    administrateurRoute,
    administrateurPopupRoute
} from './';

const ENTITY_STATES = [...administrateurRoute, ...administrateurPopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AdministrateurComponent,
        AdministrateurDetailComponent,
        AdministrateurUpdateComponent,
        AdministrateurDeleteDialogComponent,
        AdministrateurDeletePopupComponent
    ],
    entryComponents: [
        AdministrateurComponent,
        AdministrateurUpdateComponent,
        AdministrateurDeleteDialogComponent,
        AdministrateurDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceAdministrateurModule {}
