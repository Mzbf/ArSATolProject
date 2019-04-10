import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IZoneGeo } from 'app/shared/model/zone-geo.model';

@Component({
    selector: 'jhi-zone-geo-detail',
    templateUrl: './zone-geo-detail.component.html'
})
export class ZoneGeoDetailComponent implements OnInit {
    zoneGeo: IZoneGeo;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ zoneGeo }) => {
            this.zoneGeo = zoneGeo;
        });
    }

    previousState() {
        window.history.back();
    }
}
