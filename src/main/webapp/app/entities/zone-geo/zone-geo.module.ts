import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArsatollserviceSharedModule } from 'app/shared';
import {
    ZoneGeoComponent,
    ZoneGeoDetailComponent,
    ZoneGeoUpdateComponent,
    ZoneGeoDeletePopupComponent,
    ZoneGeoDeleteDialogComponent,
    zoneGeoRoute,
    zoneGeoPopupRoute
} from './';

const ENTITY_STATES = [...zoneGeoRoute, ...zoneGeoPopupRoute];

@NgModule({
    imports: [ArsatollserviceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ZoneGeoComponent,
        ZoneGeoDetailComponent,
        ZoneGeoUpdateComponent,
        ZoneGeoDeleteDialogComponent,
        ZoneGeoDeletePopupComponent
    ],
    entryComponents: [ZoneGeoComponent, ZoneGeoUpdateComponent, ZoneGeoDeleteDialogComponent, ZoneGeoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceZoneGeoModule {}
