import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Herbe } from 'app/shared/model/herbe.model';
import { HerbeService } from './herbe.service';
import { HerbeComponent } from './herbe.component';
import { HerbeDetailComponent } from './herbe-detail.component';
import { HerbeUpdateComponent } from './herbe-update.component';
import { HerbeDeletePopupComponent } from './herbe-delete-dialog.component';
import { IHerbe } from 'app/shared/model/herbe.model';

@Injectable({ providedIn: 'root' })
export class HerbeResolve implements Resolve<IHerbe> {
    constructor(private service: HerbeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IHerbe> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Herbe>) => response.ok),
                map((herbe: HttpResponse<Herbe>) => herbe.body)
            );
        }
        return of(new Herbe());
    }
}

export const herbeRoute: Routes = [
    {
        path: '',
        component: HerbeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Herbes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: HerbeDetailComponent,
        resolve: {
            herbe: HerbeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Herbes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: HerbeUpdateComponent,
        resolve: {
            herbe: HerbeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Herbes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: HerbeUpdateComponent,
        resolve: {
            herbe: HerbeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Herbes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const herbePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: HerbeDeletePopupComponent,
        resolve: {
            herbe: HerbeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Herbes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
