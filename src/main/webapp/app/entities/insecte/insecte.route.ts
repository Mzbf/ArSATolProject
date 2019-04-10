import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Insecte } from 'app/shared/model/insecte.model';
import { InsecteService } from './insecte.service';
import { InsecteComponent } from './insecte.component';
import { InsecteDetailComponent } from './insecte-detail.component';
import { InsecteUpdateComponent } from './insecte-update.component';
import { InsecteDeletePopupComponent } from './insecte-delete-dialog.component';
import { IInsecte } from 'app/shared/model/insecte.model';

@Injectable({ providedIn: 'root' })
export class InsecteResolve implements Resolve<IInsecte> {
    constructor(private service: InsecteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IInsecte> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Insecte>) => response.ok),
                map((insecte: HttpResponse<Insecte>) => insecte.body)
            );
        }
        return of(new Insecte());
    }
}

export const insecteRoute: Routes = [
    {
        path: '',
        component: InsecteComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Insectes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: InsecteDetailComponent,
        resolve: {
            insecte: InsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Insectes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: InsecteUpdateComponent,
        resolve: {
            insecte: InsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Insectes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: InsecteUpdateComponent,
        resolve: {
            insecte: InsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Insectes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const insectePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: InsecteDeletePopupComponent,
        resolve: {
            insecte: InsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Insectes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
