import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TypeInsecte } from 'app/shared/model/type-insecte.model';
import { TypeInsecteService } from './type-insecte.service';
import { TypeInsecteComponent } from './type-insecte.component';
import { TypeInsecteDetailComponent } from './type-insecte-detail.component';
import { TypeInsecteUpdateComponent } from './type-insecte-update.component';
import { TypeInsecteDeletePopupComponent } from './type-insecte-delete-dialog.component';
import { ITypeInsecte } from 'app/shared/model/type-insecte.model';

@Injectable({ providedIn: 'root' })
export class TypeInsecteResolve implements Resolve<ITypeInsecte> {
    constructor(private service: TypeInsecteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITypeInsecte> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TypeInsecte>) => response.ok),
                map((typeInsecte: HttpResponse<TypeInsecte>) => typeInsecte.body)
            );
        }
        return of(new TypeInsecte());
    }
}

export const typeInsecteRoute: Routes = [
    {
        path: '',
        component: TypeInsecteComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeInsectes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TypeInsecteDetailComponent,
        resolve: {
            typeInsecte: TypeInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeInsectes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TypeInsecteUpdateComponent,
        resolve: {
            typeInsecte: TypeInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeInsectes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TypeInsecteUpdateComponent,
        resolve: {
            typeInsecte: TypeInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeInsectes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typeInsectePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TypeInsecteDeletePopupComponent,
        resolve: {
            typeInsecte: TypeInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeInsectes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
