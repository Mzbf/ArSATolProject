import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MethodeLutte } from 'app/shared/model/methode-lutte.model';
import { MethodeLutteService } from './methode-lutte.service';
import { MethodeLutteComponent } from './methode-lutte.component';
import { MethodeLutteDetailComponent } from './methode-lutte-detail.component';
import { MethodeLutteUpdateComponent } from './methode-lutte-update.component';
import { MethodeLutteDeletePopupComponent } from './methode-lutte-delete-dialog.component';
import { IMethodeLutte } from 'app/shared/model/methode-lutte.model';

@Injectable({ providedIn: 'root' })
export class MethodeLutteResolve implements Resolve<IMethodeLutte> {
    constructor(private service: MethodeLutteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMethodeLutte> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<MethodeLutte>) => response.ok),
                map((methodeLutte: HttpResponse<MethodeLutte>) => methodeLutte.body)
            );
        }
        return of(new MethodeLutte());
    }
}

export const methodeLutteRoute: Routes = [
    {
        path: '',
        component: MethodeLutteComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MethodeLuttes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: MethodeLutteDetailComponent,
        resolve: {
            methodeLutte: MethodeLutteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MethodeLuttes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: MethodeLutteUpdateComponent,
        resolve: {
            methodeLutte: MethodeLutteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MethodeLuttes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: MethodeLutteUpdateComponent,
        resolve: {
            methodeLutte: MethodeLutteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MethodeLuttes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const methodeLuttePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: MethodeLutteDeletePopupComponent,
        resolve: {
            methodeLutte: MethodeLutteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MethodeLuttes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
