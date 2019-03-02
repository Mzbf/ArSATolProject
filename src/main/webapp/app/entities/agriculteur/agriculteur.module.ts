import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    AgriculteurComponent,
    AgriculteurDetailComponent,
    AgriculteurUpdateComponent,
    AgriculteurDeletePopupComponent,
    AgriculteurDeleteDialogComponent,
    agriculteurRoute,
    agriculteurPopupRoute
} from './';

const ENTITY_STATES = [...agriculteurRoute, ...agriculteurPopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AgriculteurComponent,
        AgriculteurDetailComponent,
        AgriculteurUpdateComponent,
        AgriculteurDeleteDialogComponent,
        AgriculteurDeletePopupComponent
    ],
    entryComponents: [AgriculteurComponent, AgriculteurUpdateComponent, AgriculteurDeleteDialogComponent, AgriculteurDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceAgriculteurModule {}
