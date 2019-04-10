import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Chercheur } from 'app/shared/model/chercheur.model';
import { ChercheurService } from './chercheur.service';
import { ChercheurComponent } from './chercheur.component';
import { ChercheurDetailComponent } from './chercheur-detail.component';
import { ChercheurUpdateComponent } from './chercheur-update.component';
import { ChercheurDeletePopupComponent } from './chercheur-delete-dialog.component';
import { IChercheur } from 'app/shared/model/chercheur.model';

@Injectable({ providedIn: 'root' })
export class ChercheurResolve implements Resolve<IChercheur> {
    constructor(private service: ChercheurService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IChercheur> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Chercheur>) => response.ok),
                map((chercheur: HttpResponse<Chercheur>) => chercheur.body)
            );
        }
        return of(new Chercheur());
    }
}

export const chercheurRoute: Routes = [
    {
        path: '',
        component: ChercheurComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Chercheurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ChercheurDetailComponent,
        resolve: {
            chercheur: ChercheurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Chercheurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ChercheurUpdateComponent,
        resolve: {
            chercheur: ChercheurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Chercheurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ChercheurUpdateComponent,
        resolve: {
            chercheur: ChercheurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Chercheurs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const chercheurPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ChercheurDeletePopupComponent,
        resolve: {
            chercheur: ChercheurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Chercheurs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
