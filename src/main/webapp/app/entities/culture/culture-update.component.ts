import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICulture } from 'app/shared/model/culture.model';
import { CultureService } from './culture.service';
import { IMaladie } from 'app/shared/model/maladie.model';
import { MaladieService } from 'app/entities/maladie';
import { IHerbe } from 'app/shared/model/herbe.model';
import { HerbeService } from 'app/entities/herbe';
import { IZoneGeo } from 'app/shared/model/zone-geo.model';
import { ZoneGeoService } from 'app/entities/zone-geo';
import { ITypeCulture } from 'app/shared/model/type-culture.model';
import { TypeCultureService } from 'app/entities/type-culture';

@Component({
    selector: 'jhi-culture-update',
    templateUrl: './culture-update.component.html'
})
export class CultureUpdateComponent implements OnInit {
    culture: ICulture;
    isSaving: boolean;

    maladies: IMaladie[];

    herbes: IHerbe[];

    zonegeos: IZoneGeo[];

    typecultures: ITypeCulture[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected cultureService: CultureService,
        protected maladieService: MaladieService,
        protected herbeService: HerbeService,
        protected zoneGeoService: ZoneGeoService,
        protected typeCultureService: TypeCultureService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ culture }) => {
            this.culture = culture;
        });
        this.maladieService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IMaladie[]>) => mayBeOk.ok),
                map((response: HttpResponse<IMaladie[]>) => response.body)
            )
            .subscribe((res: IMaladie[]) => (this.maladies = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.herbeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IHerbe[]>) => mayBeOk.ok),
                map((response: HttpResponse<IHerbe[]>) => response.body)
            )
            .subscribe((res: IHerbe[]) => (this.herbes = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.zoneGeoService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IZoneGeo[]>) => mayBeOk.ok),
                map((response: HttpResponse<IZoneGeo[]>) => response.body)
            )
            .subscribe((res: IZoneGeo[]) => (this.zonegeos = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.typeCultureService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ITypeCulture[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITypeCulture[]>) => response.body)
            )
            .subscribe((res: ITypeCulture[]) => (this.typecultures = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.culture.id !== undefined) {
            this.subscribeToSaveResponse(this.cultureService.update(this.culture));
        } else {
            this.subscribeToSaveResponse(this.cultureService.create(this.culture));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICulture>>) {
        result.subscribe((res: HttpResponse<ICulture>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackMaladieById(index: number, item: IMaladie) {
        return item.id;
    }

    trackHerbeById(index: number, item: IHerbe) {
        return item.id;
    }

    trackZoneGeoById(index: number, item: IZoneGeo) {
        return item.id;
    }

    trackTypeCultureById(index: number, item: ITypeCulture) {
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
