import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ChercheurInsecte } from 'app/shared/model/chercheur-insecte.model';
import { ChercheurInsecteService } from './chercheur-insecte.service';
import { ChercheurInsecteComponent } from './chercheur-insecte.component';
import { ChercheurInsecteDetailComponent } from './chercheur-insecte-detail.component';
import { ChercheurInsecteUpdateComponent } from './chercheur-insecte-update.component';
import { ChercheurInsecteDeletePopupComponent } from './chercheur-insecte-delete-dialog.component';
import { IChercheurInsecte } from 'app/shared/model/chercheur-insecte.model';

@Injectable({ providedIn: 'root' })
export class ChercheurInsecteResolve implements Resolve<IChercheurInsecte> {
    constructor(private service: ChercheurInsecteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IChercheurInsecte> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ChercheurInsecte>) => response.ok),
                map((chercheurInsecte: HttpResponse<ChercheurInsecte>) => chercheurInsecte.body)
            );
        }
        return of(new ChercheurInsecte());
    }
}

export const chercheurInsecteRoute: Routes = [
    {
        path: '',
        component: ChercheurInsecteComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ChercheurInsectes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ChercheurInsecteDetailComponent,
        resolve: {
            chercheurInsecte: ChercheurInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ChercheurInsectes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ChercheurInsecteUpdateComponent,
        resolve: {
            chercheurInsecte: ChercheurInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ChercheurInsectes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ChercheurInsecteUpdateComponent,
        resolve: {
            chercheurInsecte: ChercheurInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ChercheurInsectes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const chercheurInsectePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ChercheurInsecteDeletePopupComponent,
        resolve: {
            chercheurInsecte: ChercheurInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ChercheurInsectes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
