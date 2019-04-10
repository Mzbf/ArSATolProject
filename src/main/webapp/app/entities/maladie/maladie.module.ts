import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    MaladieComponent,
    MaladieDetailComponent,
    MaladieUpdateComponent,
    MaladieDeletePopupComponent,
    MaladieDeleteDialogComponent,
    maladieRoute,
    maladiePopupRoute
} from './';

const ENTITY_STATES = [...maladieRoute, ...maladiePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MaladieComponent,
        MaladieDetailComponent,
        MaladieUpdateComponent,
        MaladieDeleteDialogComponent,
        MaladieDeletePopupComponent
    ],
    entryComponents: [MaladieComponent, MaladieUpdateComponent, MaladieDeleteDialogComponent, MaladieDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceMaladieModule {}
