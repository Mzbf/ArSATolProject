import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ImageMaladie } from 'app/shared/model/image-maladie.model';
import { ImageMaladieService } from './image-maladie.service';
import { ImageMaladieComponent } from './image-maladie.component';
import { ImageMaladieDetailComponent } from './image-maladie-detail.component';
import { ImageMaladieUpdateComponent } from './image-maladie-update.component';
import { ImageMaladieDeletePopupComponent } from './image-maladie-delete-dialog.component';
import { IImageMaladie } from 'app/shared/model/image-maladie.model';

@Injectable({ providedIn: 'root' })
export class ImageMaladieResolve implements Resolve<IImageMaladie> {
    constructor(private service: ImageMaladieService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IImageMaladie> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ImageMaladie>) => response.ok),
                map((imageMaladie: HttpResponse<ImageMaladie>) => imageMaladie.body)
            );
        }
        return of(new ImageMaladie());
    }
}

export const imageMaladieRoute: Routes = [
    {
        path: '',
        component: ImageMaladieComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageMaladies'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ImageMaladieDetailComponent,
        resolve: {
            imageMaladie: ImageMaladieResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageMaladies'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ImageMaladieUpdateComponent,
        resolve: {
            imageMaladie: ImageMaladieResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageMaladies'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ImageMaladieUpdateComponent,
        resolve: {
            imageMaladie: ImageMaladieResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageMaladies'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const imageMaladiePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ImageMaladieDeletePopupComponent,
        resolve: {
            imageMaladie: ImageMaladieResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageMaladies'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
