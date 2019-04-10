import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ImageCulture } from 'app/shared/model/image-culture.model';
import { ImageCultureService } from './image-culture.service';
import { ImageCultureComponent } from './image-culture.component';
import { ImageCultureDetailComponent } from './image-culture-detail.component';
import { ImageCultureUpdateComponent } from './image-culture-update.component';
import { ImageCultureDeletePopupComponent } from './image-culture-delete-dialog.component';
import { IImageCulture } from 'app/shared/model/image-culture.model';

@Injectable({ providedIn: 'root' })
export class ImageCultureResolve implements Resolve<IImageCulture> {
    constructor(private service: ImageCultureService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IImageCulture> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ImageCulture>) => response.ok),
                map((imageCulture: HttpResponse<ImageCulture>) => imageCulture.body)
            );
        }
        return of(new ImageCulture());
    }
}

export const imageCultureRoute: Routes = [
    {
        path: '',
        component: ImageCultureComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageCultures'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ImageCultureDetailComponent,
        resolve: {
            imageCulture: ImageCultureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageCultures'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ImageCultureUpdateComponent,
        resolve: {
            imageCulture: ImageCultureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageCultures'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ImageCultureUpdateComponent,
        resolve: {
            imageCulture: ImageCultureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageCultures'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const imageCulturePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ImageCultureDeletePopupComponent,
        resolve: {
            imageCulture: ImageCultureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageCultures'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
