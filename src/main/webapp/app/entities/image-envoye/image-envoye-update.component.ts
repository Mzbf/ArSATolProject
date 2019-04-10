import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IImageEnvoye } from 'app/shared/model/image-envoye.model';
import { ImageEnvoyeService } from './image-envoye.service';
import { IAgriculteur } from 'app/shared/model/agriculteur.model';
import { AgriculteurService } from 'app/entities/agriculteur';

@Component({
    selector: 'jhi-image-envoye-update',
    templateUrl: './image-envoye-update.component.html'
})
export class ImageEnvoyeUpdateComponent implements OnInit {
    imageEnvoye: IImageEnvoye;
    isSaving: boolean;

    agriculteurs: IAgriculteur[];
    dateDAjout: string;
    dateValidation: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected imageEnvoyeService: ImageEnvoyeService,
        protected agriculteurService: AgriculteurService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ imageEnvoye }) => {
            this.imageEnvoye = imageEnvoye;
            this.dateDAjout = this.imageEnvoye.dateDAjout != null ? this.imageEnvoye.dateDAjout.format(DATE_TIME_FORMAT) : null;
            this.dateValidation = this.imageEnvoye.dateValidation != null ? this.imageEnvoye.dateValidation.format(DATE_TIME_FORMAT) : null;
        });
        this.agriculteurService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IAgriculteur[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAgriculteur[]>) => response.body)
            )
            .subscribe((res: IAgriculteur[]) => (this.agriculteurs = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.imageEnvoye.dateDAjout = this.dateDAjout != null ? moment(this.dateDAjout, DATE_TIME_FORMAT) : null;
        this.imageEnvoye.dateValidation = this.dateValidation != null ? moment(this.dateValidation, DATE_TIME_FORMAT) : null;
        if (this.imageEnvoye.id !== undefined) {
            this.subscribeToSaveResponse(this.imageEnvoyeService.update(this.imageEnvoye));
        } else {
            this.subscribeToSaveResponse(this.imageEnvoyeService.create(this.imageEnvoye));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IImageEnvoye>>) {
        result.subscribe((res: HttpResponse<IImageEnvoye>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAgriculteurById(index: number, item: IAgriculteur) {
        return item.id;
    }
}
