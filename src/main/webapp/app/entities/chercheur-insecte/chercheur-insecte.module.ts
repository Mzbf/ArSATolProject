import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    ChercheurInsecteComponent,
    ChercheurInsecteDetailComponent,
    ChercheurInsecteUpdateComponent,
    ChercheurInsecteDeletePopupComponent,
    ChercheurInsecteDeleteDialogComponent,
    chercheurInsecteRoute,
    chercheurInsectePopupRoute
} from './';

const ENTITY_STATES = [...chercheurInsecteRoute, ...chercheurInsectePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ChercheurInsecteComponent,
        ChercheurInsecteDetailComponent,
        ChercheurInsecteUpdateComponent,
        ChercheurInsecteDeleteDialogComponent,
        ChercheurInsecteDeletePopupComponent
    ],
    entryComponents: [
        ChercheurInsecteComponent,
        ChercheurInsecteUpdateComponent,
        ChercheurInsecteDeleteDialogComponent,
        ChercheurInsecteDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceChercheurInsecteModule {}
