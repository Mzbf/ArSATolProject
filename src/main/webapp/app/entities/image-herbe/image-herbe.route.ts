import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ImageHerbe } from 'app/shared/model/image-herbe.model';
import { ImageHerbeService } from './image-herbe.service';
import { ImageHerbeComponent } from './image-herbe.component';
import { ImageHerbeDetailComponent } from './image-herbe-detail.component';
import { ImageHerbeUpdateComponent } from './image-herbe-update.component';
import { ImageHerbeDeletePopupComponent } from './image-herbe-delete-dialog.component';
import { IImageHerbe } from 'app/shared/model/image-herbe.model';

@Injectable({ providedIn: 'root' })
export class ImageHerbeResolve implements Resolve<IImageHerbe> {
    constructor(private service: ImageHerbeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IImageHerbe> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ImageHerbe>) => response.ok),
                map((imageHerbe: HttpResponse<ImageHerbe>) => imageHerbe.body)
            );
        }
        return of(new ImageHerbe());
    }
}

export const imageHerbeRoute: Routes = [
    {
        path: '',
        component: ImageHerbeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageHerbes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ImageHerbeDetailComponent,
        resolve: {
            imageHerbe: ImageHerbeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageHerbes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ImageHerbeUpdateComponent,
        resolve: {
            imageHerbe: ImageHerbeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageHerbes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ImageHerbeUpdateComponent,
        resolve: {
            imageHerbe: ImageHerbeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageHerbes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const imageHerbePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ImageHerbeDeletePopupComponent,
        resolve: {
            imageHerbe: ImageHerbeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageHerbes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
