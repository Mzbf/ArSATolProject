import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SuperFamille } from 'app/shared/model/super-famille.model';
import { SuperFamilleService } from './super-famille.service';
import { SuperFamilleComponent } from './super-famille.component';
import { SuperFamilleDetailComponent } from './super-famille-detail.component';
import { SuperFamilleUpdateComponent } from './super-famille-update.component';
import { SuperFamilleDeletePopupComponent } from './super-famille-delete-dialog.component';
import { ISuperFamille } from 'app/shared/model/super-famille.model';

@Injectable({ providedIn: 'root' })
export class SuperFamilleResolve implements Resolve<ISuperFamille> {
    constructor(private service: SuperFamilleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISuperFamille> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SuperFamille>) => response.ok),
                map((superFamille: HttpResponse<SuperFamille>) => superFamille.body)
            );
        }
        return of(new SuperFamille());
    }
}

export const superFamilleRoute: Routes = [
    {
        path: '',
        component: SuperFamilleComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuperFamilles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SuperFamilleDetailComponent,
        resolve: {
            superFamille: SuperFamilleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuperFamilles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SuperFamilleUpdateComponent,
        resolve: {
            superFamille: SuperFamilleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuperFamilles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SuperFamilleUpdateComponent,
        resolve: {
            superFamille: SuperFamilleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuperFamilles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const superFamillePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SuperFamilleDeletePopupComponent,
        resolve: {
            superFamille: SuperFamilleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuperFamilles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
