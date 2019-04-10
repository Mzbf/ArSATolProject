import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    InsecteUtileComponent,
    InsecteUtileDetailComponent,
    InsecteUtileUpdateComponent,
    InsecteUtileDeletePopupComponent,
    InsecteUtileDeleteDialogComponent,
    insecteUtileRoute,
    insecteUtilePopupRoute
} from './';

const ENTITY_STATES = [...insecteUtileRoute, ...insecteUtilePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InsecteUtileComponent,
        InsecteUtileDetailComponent,
        InsecteUtileUpdateComponent,
        InsecteUtileDeleteDialogComponent,
        InsecteUtileDeletePopupComponent
    ],
    entryComponents: [
        InsecteUtileComponent,
        InsecteUtileUpdateComponent,
        InsecteUtileDeleteDialogComponent,
        InsecteUtileDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceInsecteUtileModule {}
