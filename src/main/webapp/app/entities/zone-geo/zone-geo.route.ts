import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ZoneGeo } from 'app/shared/model/zone-geo.model';
import { ZoneGeoService } from './zone-geo.service';
import { ZoneGeoComponent } from './zone-geo.component';
import { ZoneGeoDetailComponent } from './zone-geo-detail.component';
import { ZoneGeoUpdateComponent } from './zone-geo-update.component';
import { ZoneGeoDeletePopupComponent } from './zone-geo-delete-dialog.component';
import { IZoneGeo } from 'app/shared/model/zone-geo.model';

@Injectable({ providedIn: 'root' })
export class ZoneGeoResolve implements Resolve<IZoneGeo> {
    constructor(private service: ZoneGeoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IZoneGeo> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ZoneGeo>) => response.ok),
                map((zoneGeo: HttpResponse<ZoneGeo>) => zoneGeo.body)
            );
        }
        return of(new ZoneGeo());
    }
}

export const zoneGeoRoute: Routes = [
    {
        path: '',
        component: ZoneGeoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ZoneGeos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ZoneGeoDetailComponent,
        resolve: {
            zoneGeo: ZoneGeoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ZoneGeos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ZoneGeoUpdateComponent,
        resolve: {
            zoneGeo: ZoneGeoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ZoneGeos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ZoneGeoUpdateComponent,
        resolve: {
            zoneGeo: ZoneGeoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ZoneGeos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const zoneGeoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ZoneGeoDeletePopupComponent,
        resolve: {
            zoneGeo: ZoneGeoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ZoneGeos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
