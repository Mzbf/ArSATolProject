import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { InsecteRavageur } from 'app/shared/model/insecte-ravageur.model';
import { InsecteRavageurService } from './insecte-ravageur.service';
import { InsecteRavageurComponent } from './insecte-ravageur.component';
import { InsecteRavageurDetailComponent } from './insecte-ravageur-detail.component';
import { InsecteRavageurUpdateComponent } from './insecte-ravageur-update.component';
import { InsecteRavageurDeletePopupComponent } from './insecte-ravageur-delete-dialog.component';
import { IInsecteRavageur } from 'app/shared/model/insecte-ravageur.model';

@Injectable({ providedIn: 'root' })
export class InsecteRavageurResolve implements Resolve<IInsecteRavageur> {
    constructor(private service: InsecteRavageurService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IInsecteRavageur> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<InsecteRavageur>) => response.ok),
                map((insecteRavageur: HttpResponse<InsecteRavageur>) => insecteRavageur.body)
            );
        }
        return of(new InsecteRavageur());
    }
}

export const insecteRavageurRoute: Routes = [
    {
        path: '',
        component: InsecteRavageurComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'InsecteRavageurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: InsecteRavageurDetailComponent,
        resolve: {
            insecteRavageur: InsecteRavageurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'InsecteRavageurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: InsecteRavageurUpdateComponent,
        resolve: {
            insecteRavageur: InsecteRavageurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'InsecteRavageurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: InsecteRavageurUpdateComponent,
        resolve: {
            insecteRavageur: InsecteRavageurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'InsecteRavageurs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const insecteRavageurPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: InsecteRavageurDeletePopupComponent,
        resolve: {
            insecteRavageur: InsecteRavageurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'InsecteRavageurs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
