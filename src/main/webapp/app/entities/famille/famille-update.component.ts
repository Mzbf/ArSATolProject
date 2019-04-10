import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IFamille } from 'app/shared/model/famille.model';
import { FamilleService } from './famille.service';
import { IOrdre } from 'app/shared/model/ordre.model';
import { OrdreService } from 'app/entities/ordre';

@Component({
    selector: 'jhi-famille-update',
    templateUrl: './famille-update.component.html'
})
export class FamilleUpdateComponent implements OnInit {
    famille: IFamille;
    isSaving: boolean;

    ordres: IOrdre[];

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected familleService: FamilleService,
        protected ordreService: OrdreService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ famille }) => {
            this.famille = famille;
        });
        this.ordreService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IOrdre[]>) => mayBeOk.ok),
                map((response: HttpResponse<IOrdre[]>) => response.body)
            )
            .subscribe((res: IOrdre[]) => (this.ordres = res), (res: HttpErrorResponse) => this.onError(res.message));
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
        if (this.famille.id !== undefined) {
            this.subscribeToSaveResponse(this.familleService.update(this.famille));
        } else {
            this.subscribeToSaveResponse(this.familleService.create(this.famille));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IFamille>>) {
        result.subscribe((res: HttpResponse<IFamille>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackOrdreById(index: number, item: IOrdre) {
        return item.id;
    }
}
