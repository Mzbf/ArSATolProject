import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IZoneGeo } from 'app/shared/model/zone-geo.model';
import { ZoneGeoService } from './zone-geo.service';
import { ICulture } from 'app/shared/model/culture.model';
import { CultureService } from 'app/entities/culture';

@Component({
    selector: 'jhi-zone-geo-update',
    templateUrl: './zone-geo-update.component.html'
})
export class ZoneGeoUpdateComponent implements OnInit {
    zoneGeo: IZoneGeo;
    isSaving: boolean;

    cultures: ICulture[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected zoneGeoService: ZoneGeoService,
        protected cultureService: CultureService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ zoneGeo }) => {
            this.zoneGeo = zoneGeo;
        });
        this.cultureService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICulture[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICulture[]>) => response.body)
            )
            .subscribe((res: ICulture[]) => (this.cultures = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.zoneGeo.id !== undefined) {
            this.subscribeToSaveResponse(this.zoneGeoService.update(this.zoneGeo));
        } else {
            this.subscribeToSaveResponse(this.zoneGeoService.create(this.zoneGeo));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IZoneGeo>>) {
        result.subscribe((res: HttpResponse<IZoneGeo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCultureById(index: number, item: ICulture) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
