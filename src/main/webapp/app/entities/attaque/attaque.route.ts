import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Attaque } from 'app/shared/model/attaque.model';
import { AttaqueService } from './attaque.service';
import { AttaqueComponent } from './attaque.component';
import { AttaqueDetailComponent } from './attaque-detail.component';
import { AttaqueUpdateComponent } from './attaque-update.component';
import { AttaqueDeletePopupComponent } from './attaque-delete-dialog.component';
import { IAttaque } from 'app/shared/model/attaque.model';

@Injectable({ providedIn: 'root' })
export class AttaqueResolve implements Resolve<IAttaque> {
    constructor(private service: AttaqueService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAttaque> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Attaque>) => response.ok),
                map((attaque: HttpResponse<Attaque>) => attaque.body)
            );
        }
        return of(new Attaque());
    }
}

export const attaqueRoute: Routes = [
    {
        path: '',
        component: AttaqueComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attaques'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AttaqueDetailComponent,
        resolve: {
            attaque: AttaqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attaques'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AttaqueUpdateComponent,
        resolve: {
            attaque: AttaqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attaques'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AttaqueUpdateComponent,
        resolve: {
            attaque: AttaqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attaques'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const attaquePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AttaqueDeletePopupComponent,
        resolve: {
            attaque: AttaqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attaques'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
