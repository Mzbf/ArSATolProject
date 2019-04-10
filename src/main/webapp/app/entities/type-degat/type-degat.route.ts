import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TypeDegat } from 'app/shared/model/type-degat.model';
import { TypeDegatService } from './type-degat.service';
import { TypeDegatComponent } from './type-degat.component';
import { TypeDegatDetailComponent } from './type-degat-detail.component';
import { TypeDegatUpdateComponent } from './type-degat-update.component';
import { TypeDegatDeletePopupComponent } from './type-degat-delete-dialog.component';
import { ITypeDegat } from 'app/shared/model/type-degat.model';

@Injectable({ providedIn: 'root' })
export class TypeDegatResolve implements Resolve<ITypeDegat> {
    constructor(private service: TypeDegatService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITypeDegat> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TypeDegat>) => response.ok),
                map((typeDegat: HttpResponse<TypeDegat>) => typeDegat.body)
            );
        }
        return of(new TypeDegat());
    }
}

export const typeDegatRoute: Routes = [
    {
        path: '',
        component: TypeDegatComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeDegats'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TypeDegatDetailComponent,
        resolve: {
            typeDegat: TypeDegatResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeDegats'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TypeDegatUpdateComponent,
        resolve: {
            typeDegat: TypeDegatResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeDegats'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TypeDegatUpdateComponent,
        resolve: {
            typeDegat: TypeDegatResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeDegats'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typeDegatPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TypeDegatDeletePopupComponent,
        resolve: {
            typeDegat: TypeDegatResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeDegats'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
