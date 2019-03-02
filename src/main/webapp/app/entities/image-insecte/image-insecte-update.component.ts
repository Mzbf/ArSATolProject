import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IImageInsecte } from 'app/shared/model/image-insecte.model';
import { ImageInsecteService } from './image-insecte.service';
import { IImage } from 'app/shared/model/image.model';
import { ImageService } from 'app/entities/image';
import { IAgriculteur } from 'app/shared/model/agriculteur.model';
import { AgriculteurService } from 'app/entities/agriculteur';

@Component({
    selector: 'jhi-image-insecte-update',
    templateUrl: './image-insecte-update.component.html'
})
export class ImageInsecteUpdateComponent implements OnInit {
    imageInsecte: IImageInsecte;
    isSaving: boolean;

    images: IImage[];

    agriculteurs: IAgriculteur[];
    dateDAjout: string;
    dateValidation: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected imageInsecteService: ImageInsecteService,
        protected imageService: ImageService,
        protected agriculteurService: AgriculteurService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ imageInsecte }) => {
            this.imageInsecte = imageInsecte;
            this.dateDAjout = this.imageInsecte.dateDAjout != null ? this.imageInsecte.dateDAjout.format(DATE_TIME_FORMAT) : null;
            this.dateValidation =
                this.imageInsecte.dateValidation != null ? this.imageInsecte.dateValidation.format(DATE_TIME_FORMAT) : null;
        });
        this.imageService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IImage[]>) => mayBeOk.ok),
                map((response: HttpResponse<IImage[]>) => response.body)
            )
            .subscribe((res: IImage[]) => (this.images = res), (res: HttpErrorResponse) => this.onError(res.message));
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
        this.imageInsecte.dateDAjout = this.dateDAjout != null ? moment(this.dateDAjout, DATE_TIME_FORMAT) : null;
        this.imageInsecte.dateValidation = this.dateValidation != null ? moment(this.dateValidation, DATE_TIME_FORMAT) : null;
        if (this.imageInsecte.id !== undefined) {
            this.subscribeToSaveResponse(this.imageInsecteService.update(this.imageInsecte));
        } else {
            this.subscribeToSaveResponse(this.imageInsecteService.create(this.imageInsecte));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IImageInsecte>>) {
        result.subscribe((res: HttpResponse<IImageInsecte>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackImageById(index: number, item: IImage) {
        return item.id;
    }

    trackAgriculteurById(index: number, item: IAgriculteur) {
        return item.id;
    }
}
