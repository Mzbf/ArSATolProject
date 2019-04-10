import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    TypeInsecteComponent,
    TypeInsecteDetailComponent,
    TypeInsecteUpdateComponent,
    TypeInsecteDeletePopupComponent,
    TypeInsecteDeleteDialogComponent,
    typeInsecteRoute,
    typeInsectePopupRoute
} from './';

const ENTITY_STATES = [...typeInsecteRoute, ...typeInsectePopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TypeInsecteComponent,
        TypeInsecteDetailComponent,
        TypeInsecteUpdateComponent,
        TypeInsecteDeleteDialogComponent,
        TypeInsecteDeletePopupComponent
    ],
    entryComponents: [TypeInsecteComponent, TypeInsecteUpdateComponent, TypeInsecteDeleteDialogComponent, TypeInsecteDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceTypeInsecteModule {}
