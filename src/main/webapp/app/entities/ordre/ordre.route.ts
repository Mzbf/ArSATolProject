import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Ordre } from 'app/shared/model/ordre.model';
import { OrdreService } from './ordre.service';
import { OrdreComponent } from './ordre.component';
import { OrdreDetailComponent } from './ordre-detail.component';
import { OrdreUpdateComponent } from './ordre-update.component';
import { OrdreDeletePopupComponent } from './ordre-delete-dialog.component';
import { IOrdre } from 'app/shared/model/ordre.model';

@Injectable({ providedIn: 'root' })
export class OrdreResolve implements Resolve<IOrdre> {
    constructor(private service: OrdreService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IOrdre> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Ordre>) => response.ok),
                map((ordre: HttpResponse<Ordre>) => ordre.body)
            );
        }
        return of(new Ordre());
    }
}

export const ordreRoute: Routes = [
    {
        path: '',
        component: OrdreComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Ordres'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: OrdreDetailComponent,
        resolve: {
            ordre: OrdreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Ordres'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: OrdreUpdateComponent,
        resolve: {
            ordre: OrdreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Ordres'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: OrdreUpdateComponent,
        resolve: {
            ordre: OrdreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Ordres'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ordrePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: OrdreDeletePopupComponent,
        resolve: {
            ordre: OrdreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Ordres'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
