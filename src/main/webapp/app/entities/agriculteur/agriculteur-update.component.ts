import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IAgriculteur } from 'app/shared/model/agriculteur.model';
import { AgriculteurService } from './agriculteur.service';
import { IZoneGeo } from 'app/shared/model/zone-geo.model';
import { ZoneGeoService } from 'app/entities/zone-geo';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-agriculteur-update',
    templateUrl: './agriculteur-update.component.html'
})
export class AgriculteurUpdateComponent implements OnInit {
    agriculteur: IAgriculteur;
    isSaving: boolean;

    pays: IZoneGeo[];

    users: IUser[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected agriculteurService: AgriculteurService,
        protected zoneGeoService: ZoneGeoService,
        protected userService: UserService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ agriculteur }) => {
            this.agriculteur = agriculteur;
        });
        this.zoneGeoService
            .query({ filter: 'agriculteur-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IZoneGeo[]>) => mayBeOk.ok),
                map((response: HttpResponse<IZoneGeo[]>) => response.body)
            )
            .subscribe(
                (res: IZoneGeo[]) => {
                    if (!this.agriculteur.paysId) {
                        this.pays = res;
                    } else {
                        this.zoneGeoService
                            .find(this.agriculteur.paysId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IZoneGeo>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IZoneGeo>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IZoneGeo) => (this.pays = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.userService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUser[]>) => response.body)
            )
            .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.agriculteur.id !== undefined) {
            this.subscribeToSaveResponse(this.agriculteurService.update(this.agriculteur));
        } else {
            this.subscribeToSaveResponse(this.agriculteurService.create(this.agriculteur));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAgriculteur>>) {
        result.subscribe((res: HttpResponse<IAgriculteur>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackZoneGeoById(index: number, item: IZoneGeo) {
        return item.id;
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
}
