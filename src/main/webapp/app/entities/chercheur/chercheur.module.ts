import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    ChercheurComponent,
    ChercheurDetailComponent,
    ChercheurUpdateComponent,
    ChercheurDeletePopupComponent,
    ChercheurDeleteDialogComponent,
    chercheurRoute,
    chercheurPopupRoute
} from './';

const ENTITY_STATES = [...chercheurRoute, ...chercheurPopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ChercheurComponent,
        ChercheurDetailComponent,
        ChercheurUpdateComponent,
        ChercheurDeleteDialogComponent,
        ChercheurDeletePopupComponent
    ],
    entryComponents: [ChercheurComponent, ChercheurUpdateComponent, ChercheurDeleteDialogComponent, ChercheurDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceChercheurModule {}
