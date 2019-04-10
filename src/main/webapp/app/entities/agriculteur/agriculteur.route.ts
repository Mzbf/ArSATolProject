import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Agriculteur } from 'app/shared/model/agriculteur.model';
import { AgriculteurService } from './agriculteur.service';
import { AgriculteurComponent } from './agriculteur.component';
import { AgriculteurDetailComponent } from './agriculteur-detail.component';
import { AgriculteurUpdateComponent } from './agriculteur-update.component';
import { AgriculteurDeletePopupComponent } from './agriculteur-delete-dialog.component';
import { IAgriculteur } from 'app/shared/model/agriculteur.model';

@Injectable({ providedIn: 'root' })
export class AgriculteurResolve implements Resolve<IAgriculteur> {
    constructor(private service: AgriculteurService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAgriculteur> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Agriculteur>) => response.ok),
                map((agriculteur: HttpResponse<Agriculteur>) => agriculteur.body)
            );
        }
        return of(new Agriculteur());
    }
}

export const agriculteurRoute: Routes = [
    {
        path: '',
        component: AgriculteurComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Agriculteurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AgriculteurDetailComponent,
        resolve: {
            agriculteur: AgriculteurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Agriculteurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AgriculteurUpdateComponent,
        resolve: {
            agriculteur: AgriculteurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Agriculteurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AgriculteurUpdateComponent,
        resolve: {
            agriculteur: AgriculteurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Agriculteurs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const agriculteurPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AgriculteurDeletePopupComponent,
        resolve: {
            agriculteur: AgriculteurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Agriculteurs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
