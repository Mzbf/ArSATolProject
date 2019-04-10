import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ImageAttaque } from 'app/shared/model/image-attaque.model';
import { ImageAttaqueService } from './image-attaque.service';
import { ImageAttaqueComponent } from './image-attaque.component';
import { ImageAttaqueDetailComponent } from './image-attaque-detail.component';
import { ImageAttaqueUpdateComponent } from './image-attaque-update.component';
import { ImageAttaqueDeletePopupComponent } from './image-attaque-delete-dialog.component';
import { IImageAttaque } from 'app/shared/model/image-attaque.model';

@Injectable({ providedIn: 'root' })
export class ImageAttaqueResolve implements Resolve<IImageAttaque> {
    constructor(private service: ImageAttaqueService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IImageAttaque> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ImageAttaque>) => response.ok),
                map((imageAttaque: HttpResponse<ImageAttaque>) => imageAttaque.body)
            );
        }
        return of(new ImageAttaque());
    }
}

export const imageAttaqueRoute: Routes = [
    {
        path: '',
        component: ImageAttaqueComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageAttaques'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ImageAttaqueDetailComponent,
        resolve: {
            imageAttaque: ImageAttaqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageAttaques'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ImageAttaqueUpdateComponent,
        resolve: {
            imageAttaque: ImageAttaqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageAttaques'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ImageAttaqueUpdateComponent,
        resolve: {
            imageAttaque: ImageAttaqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageAttaques'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const imageAttaquePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ImageAttaqueDeletePopupComponent,
        resolve: {
            imageAttaque: ImageAttaqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageAttaques'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
