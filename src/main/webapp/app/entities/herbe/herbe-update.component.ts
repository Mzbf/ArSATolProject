import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IHerbe } from 'app/shared/model/herbe.model';
import { HerbeService } from './herbe.service';
import { IMethodeLutte } from 'app/shared/model/methode-lutte.model';
import { MethodeLutteService } from 'app/entities/methode-lutte';
import { ICulture } from 'app/shared/model/culture.model';
import { CultureService } from 'app/entities/culture';

@Component({
    selector: 'jhi-herbe-update',
    templateUrl: './herbe-update.component.html'
})
export class HerbeUpdateComponent implements OnInit {
    herbe: IHerbe;
    isSaving: boolean;

    herbemls: IMethodeLutte[];

    cultures: ICulture[];

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected herbeService: HerbeService,
        protected methodeLutteService: MethodeLutteService,
        protected cultureService: CultureService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ herbe }) => {
            this.herbe = herbe;
        });
        this.methodeLutteService
            .query({ filter: 'herbe-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IMethodeLutte[]>) => mayBeOk.ok),
                map((response: HttpResponse<IMethodeLutte[]>) => response.body)
            )
            .subscribe(
                (res: IMethodeLutte[]) => {
                    if (!this.herbe.herbeMLId) {
                        this.herbemls = res;
                    } else {
                        this.methodeLutteService
                            .find(this.herbe.herbeMLId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IMethodeLutte>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IMethodeLutte>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IMethodeLutte) => (this.herbemls = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.cultureService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICulture[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICulture[]>) => response.body)
            )
            .subscribe((res: ICulture[]) => (this.cultures = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.herbe.id !== undefined) {
            this.subscribeToSaveResponse(this.herbeService.update(this.herbe));
        } else {
            this.subscribeToSaveResponse(this.herbeService.create(this.herbe));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IHerbe>>) {
        result.subscribe((res: HttpResponse<IHerbe>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackMethodeLutteById(index: number, item: IMethodeLutte) {
        return item.id;
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
