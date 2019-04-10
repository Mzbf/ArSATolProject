import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Demo } from 'app/shared/model/demo.model';
import { DemoService } from './demo.service';
import { DemoComponent } from './demo.component';
import { DemoDetailComponent } from './demo-detail.component';
import { DemoUpdateComponent } from './demo-update.component';
import { DemoDeletePopupComponent } from './demo-delete-dialog.component';
import { IDemo } from 'app/shared/model/demo.model';

@Injectable({ providedIn: 'root' })
export class DemoResolve implements Resolve<IDemo> {
    constructor(private service: DemoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDemo> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Demo>) => response.ok),
                map((demo: HttpResponse<Demo>) => demo.body)
            );
        }
        return of(new Demo());
    }
}

export const demoRoute: Routes = [
    {
        path: '',
        component: DemoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Demos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: DemoDetailComponent,
        resolve: {
            demo: DemoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Demos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: DemoUpdateComponent,
        resolve: {
            demo: DemoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Demos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: DemoUpdateComponent,
        resolve: {
            demo: DemoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Demos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const demoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: DemoDeletePopupComponent,
        resolve: {
            demo: DemoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Demos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
