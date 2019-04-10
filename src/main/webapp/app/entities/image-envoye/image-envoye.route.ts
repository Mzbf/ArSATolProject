import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ImageEnvoye } from 'app/shared/model/image-envoye.model';
import { ImageEnvoyeService } from './image-envoye.service';
import { ImageEnvoyeComponent } from './image-envoye.component';
import { ImageEnvoyeDetailComponent } from './image-envoye-detail.component';
import { ImageEnvoyeUpdateComponent } from './image-envoye-update.component';
import { ImageEnvoyeDeletePopupComponent } from './image-envoye-delete-dialog.component';
import { IImageEnvoye } from 'app/shared/model/image-envoye.model';

@Injectable({ providedIn: 'root' })
export class ImageEnvoyeResolve implements Resolve<IImageEnvoye> {
    constructor(private service: ImageEnvoyeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IImageEnvoye> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ImageEnvoye>) => response.ok),
                map((imageEnvoye: HttpResponse<ImageEnvoye>) => imageEnvoye.body)
            );
        }
        return of(new ImageEnvoye());
    }
}

export const imageEnvoyeRoute: Routes = [
    {
        path: '',
        component: ImageEnvoyeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageEnvoyes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ImageEnvoyeDetailComponent,
        resolve: {
            imageEnvoye: ImageEnvoyeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageEnvoyes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ImageEnvoyeUpdateComponent,
        resolve: {
            imageEnvoye: ImageEnvoyeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageEnvoyes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ImageEnvoyeUpdateComponent,
        resolve: {
            imageEnvoye: ImageEnvoyeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageEnvoyes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const imageEnvoyePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ImageEnvoyeDeletePopupComponent,
        resolve: {
            imageEnvoye: ImageEnvoyeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageEnvoyes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
