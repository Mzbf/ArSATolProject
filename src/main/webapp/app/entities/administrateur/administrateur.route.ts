import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Administrateur } from 'app/shared/model/administrateur.model';
import { AdministrateurService } from './administrateur.service';
import { AdministrateurComponent } from './administrateur.component';
import { AdministrateurDetailComponent } from './administrateur-detail.component';
import { AdministrateurUpdateComponent } from './administrateur-update.component';
import { AdministrateurDeletePopupComponent } from './administrateur-delete-dialog.component';
import { IAdministrateur } from 'app/shared/model/administrateur.model';

@Injectable({ providedIn: 'root' })
export class AdministrateurResolve implements Resolve<IAdministrateur> {
    constructor(private service: AdministrateurService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAdministrateur> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Administrateur>) => response.ok),
                map((administrateur: HttpResponse<Administrateur>) => administrateur.body)
            );
        }
        return of(new Administrateur());
    }
}

export const administrateurRoute: Routes = [
    {
        path: '',
        component: AdministrateurComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Administrateurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AdministrateurDetailComponent,
        resolve: {
            administrateur: AdministrateurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Administrateurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AdministrateurUpdateComponent,
        resolve: {
            administrateur: AdministrateurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Administrateurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AdministrateurUpdateComponent,
        resolve: {
            administrateur: AdministrateurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Administrateurs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const administrateurPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AdministrateurDeletePopupComponent,
        resolve: {
            administrateur: AdministrateurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Administrateurs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
