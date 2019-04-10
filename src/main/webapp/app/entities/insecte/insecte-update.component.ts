import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IInsecte } from 'app/shared/model/insecte.model';
import { InsecteService } from './insecte.service';
import { ITypeInsecte } from 'app/shared/model/type-insecte.model';
import { TypeInsecteService } from 'app/entities/type-insecte';

@Component({
    selector: 'jhi-insecte-update',
    templateUrl: './insecte-update.component.html'
})
export class InsecteUpdateComponent implements OnInit {
    insecte: IInsecte;
    isSaving: boolean;

    typeinsectes: ITypeInsecte[];
    dateValidation: string;
    dateAjout: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected insecteService: InsecteService,
        protected typeInsecteService: TypeInsecteService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ insecte }) => {
            this.insecte = insecte;
            this.dateValidation = this.insecte.dateValidation != null ? this.insecte.dateValidation.format(DATE_TIME_FORMAT) : null;
            this.dateAjout = this.insecte.dateAjout != null ? this.insecte.dateAjout.format(DATE_TIME_FORMAT) : null;
        });
        this.typeInsecteService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ITypeInsecte[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITypeInsecte[]>) => response.body)
            )
            .subscribe((res: ITypeInsecte[]) => (this.typeinsectes = res), (res: HttpErrorResponse) => this.onError(res.message));
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
        this.insecte.dateValidation = this.dateValidation != null ? moment(this.dateValidation, DATE_TIME_FORMAT) : null;
        this.insecte.dateAjout = this.dateAjout != null ? moment(this.dateAjout, DATE_TIME_FORMAT) : null;
        if (this.insecte.id !== undefined) {
            this.subscribeToSaveResponse(this.insecteService.update(this.insecte));
        } else {
            this.subscribeToSaveResponse(this.insecteService.create(this.insecte));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IInsecte>>) {
        result.subscribe((res: HttpResponse<IInsecte>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTypeInsecteById(index: number, item: ITypeInsecte) {
        return item.id;
    }
}
