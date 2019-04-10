import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IMaladie } from 'app/shared/model/maladie.model';
import { MaladieService } from './maladie.service';
import { IMethodeLutte } from 'app/shared/model/methode-lutte.model';
import { MethodeLutteService } from 'app/entities/methode-lutte';
import { ICulture } from 'app/shared/model/culture.model';
import { CultureService } from 'app/entities/culture';

@Component({
    selector: 'jhi-maladie-update',
    templateUrl: './maladie-update.component.html'
})
export class MaladieUpdateComponent implements OnInit {
    maladie: IMaladie;
    isSaving: boolean;

    maladiemls: IMethodeLutte[];

    cultures: ICulture[];

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected maladieService: MaladieService,
        protected methodeLutteService: MethodeLutteService,
        protected cultureService: CultureService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ maladie }) => {
            this.maladie = maladie;
        });
        this.methodeLutteService
            .query({ filter: 'maladie-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IMethodeLutte[]>) => mayBeOk.ok),
                map((response: HttpResponse<IMethodeLutte[]>) => response.body)
            )
            .subscribe(
                (res: IMethodeLutte[]) => {
                    if (!this.maladie.maladieMLId) {
                        this.maladiemls = res;
                    } else {
                        this.methodeLutteService
                            .find(this.maladie.maladieMLId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IMethodeLutte>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IMethodeLutte>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IMethodeLutte) => (this.maladiemls = [subRes].concat(res)),
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
        if (this.maladie.id !== undefined) {
            this.subscribeToSaveResponse(this.maladieService.update(this.maladie));
        } else {
            this.subscribeToSaveResponse(this.maladieService.create(this.maladie));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMaladie>>) {
        result.subscribe((res: HttpResponse<IMaladie>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
