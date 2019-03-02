import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ImageInsecte } from 'app/shared/model/image-insecte.model';
import { ImageInsecteService } from './image-insecte.service';
import { ImageInsecteComponent } from './image-insecte.component';
import { ImageInsecteDetailComponent } from './image-insecte-detail.component';
import { ImageInsecteUpdateComponent } from './image-insecte-update.component';
import { ImageInsecteDeletePopupComponent } from './image-insecte-delete-dialog.component';
import { IImageInsecte } from 'app/shared/model/image-insecte.model';

@Injectable({ providedIn: 'root' })
export class ImageInsecteResolve implements Resolve<IImageInsecte> {
    constructor(private service: ImageInsecteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IImageInsecte> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ImageInsecte>) => response.ok),
                map((imageInsecte: HttpResponse<ImageInsecte>) => imageInsecte.body)
            );
        }
        return of(new ImageInsecte());
    }
}

export const imageInsecteRoute: Routes = [
    {
        path: '',
        component: ImageInsecteComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageInsectes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ImageInsecteDetailComponent,
        resolve: {
            imageInsecte: ImageInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageInsectes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ImageInsecteUpdateComponent,
        resolve: {
            imageInsecte: ImageInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageInsectes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ImageInsecteUpdateComponent,
        resolve: {
            imageInsecte: ImageInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageInsectes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const imageInsectePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ImageInsecteDeletePopupComponent,
        resolve: {
            imageInsecte: ImageInsecteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ImageInsectes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
