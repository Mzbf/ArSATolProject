import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TypeCulture } from 'app/shared/model/type-culture.model';
import { TypeCultureService } from './type-culture.service';
import { TypeCultureComponent } from './type-culture.component';
import { TypeCultureDetailComponent } from './type-culture-detail.component';
import { TypeCultureUpdateComponent } from './type-culture-update.component';
import { TypeCultureDeletePopupComponent } from './type-culture-delete-dialog.component';
import { ITypeCulture } from 'app/shared/model/type-culture.model';

@Injectable({ providedIn: 'root' })
export class TypeCultureResolve implements Resolve<ITypeCulture> {
    constructor(private service: TypeCultureService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITypeCulture> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TypeCulture>) => response.ok),
                map((typeCulture: HttpResponse<TypeCulture>) => typeCulture.body)
            );
        }
        return of(new TypeCulture());
    }
}

export const typeCultureRoute: Routes = [
    {
        path: '',
        component: TypeCultureComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeCultures'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TypeCultureDetailComponent,
        resolve: {
            typeCulture: TypeCultureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeCultures'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TypeCultureUpdateComponent,
        resolve: {
            typeCulture: TypeCultureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeCultures'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TypeCultureUpdateComponent,
        resolve: {
            typeCulture: TypeCultureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeCultures'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typeCulturePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TypeCultureDeletePopupComponent,
        resolve: {
            typeCulture: TypeCultureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeCultures'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
