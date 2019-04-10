import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    HerbeComponent,
    HerbeDetailComponent,
    HerbeUpdateComponent,
    HerbeDeletePopupComponent,
    HerbeDeleteDialogComponent,
    herbeRoute,
    herbePopupRoute
} from './';

const ENTITY_STATES = [...herbeRoute, ...herbePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [HerbeComponent, HerbeDetailComponent, HerbeUpdateComponent, HerbeDeleteDialogComponent, HerbeDeletePopupComponent],
    entryComponents: [HerbeComponent, HerbeUpdateComponent, HerbeDeleteDialogComponent, HerbeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceHerbeModule {}
