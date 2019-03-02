import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AdminInsecte } from 'app/shared/model/admin-insecte.model';
import { AdminInsecteService } from './admin-insecte.service';
import { AdminInsecteComponent } from './admin-insecte.component';
import { AdminInsecteDetailComponent } from './admin-insecte-detail.component';
import { AdminInsecteUpdateComponent } from './admin-insecte-update.component';
import { AdminInsecteDeletePopupComponent } from './admin-insecte-delete-dialog.component';
import { IAdminInsecte } from 'app/shared/model/admin-insecte.model';

@Injectable({ providedIn: 'root' })
export class AdminInsecteResolve implements Resolve<IAdminInsecte> {
    constructor(private service: AdminInsecteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAdminInsecte> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AdminInsecte>) => response.ok),
                map((adminInsecte: HttpResponse<AdminInsecte>) => adminInsecte.body)
            );
        }
        return of(new AdminInsecte());
    }
}

export const adminInsecteRoute: Routes = [
    {
        path: '',
        component: AdminInsecteComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AdminInsectes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AdminInsecteDetailComponent,
        resolve: {
            adminInsecte: AdminInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AdminInsectes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AdminInsecteUpdateComponent,
        resolve: {
            adminInsecte: AdminInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AdminInsectes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AdminInsecteUpdateComponent,
        resolve: {
            adminInsecte: AdminInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AdminInsectes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const adminInsectePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AdminInsecteDeletePopupComponent,
        resolve: {
            adminInsecte: AdminInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AdminInsectes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
