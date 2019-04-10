import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { InsecteUtile } from 'app/shared/model/insecte-utile.model';
import { InsecteUtileService } from './insecte-utile.service';
import { InsecteUtileComponent } from './insecte-utile.component';
import { InsecteUtileDetailComponent } from './insecte-utile-detail.component';
import { InsecteUtileUpdateComponent } from './insecte-utile-update.component';
import { InsecteUtileDeletePopupComponent } from './insecte-utile-delete-dialog.component';
import { IInsecteUtile } from 'app/shared/model/insecte-utile.model';

@Injectable({ providedIn: 'root' })
export class InsecteUtileResolve implements Resolve<IInsecteUtile> {
    constructor(private service: InsecteUtileService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IInsecteUtile> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<InsecteUtile>) => response.ok),
                map((insecteUtile: HttpResponse<InsecteUtile>) => insecteUtile.body)
            );
        }
        return of(new InsecteUtile());
    }
}

export const insecteUtileRoute: Routes = [
    {
        path: '',
        component: InsecteUtileComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'InsecteUtiles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: InsecteUtileDetailComponent,
        resolve: {
            insecteUtile: InsecteUtileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'InsecteUtiles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: InsecteUtileUpdateComponent,
        resolve: {
            insecteUtile: InsecteUtileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'InsecteUtiles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: InsecteUtileUpdateComponent,
        resolve: {
            insecteUtile: InsecteUtileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'InsecteUtiles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const insecteUtilePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: InsecteUtileDeletePopupComponent,
        resolve: {
            insecteUtile: InsecteUtileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'InsecteUtiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
