import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IImage } from 'app/shared/model/image.model';
import { ImageService } from './image.service';
import { IInsecte } from 'app/shared/model/insecte.model';
import { InsecteService } from 'app/entities/insecte';
import { IAttaque } from 'app/shared/model/attaque.model';
import { AttaqueService } from 'app/entities/attaque';
import { IAgriculteur } from 'app/shared/model/agriculteur.model';
import { AgriculteurService } from 'app/entities/agriculteur';

@Component({
    selector: 'jhi-image-update',
    templateUrl: './image-update.component.html'
})
export class ImageUpdateComponent implements OnInit {
    image: IImage;
    isSaving: boolean;

    insectes: IInsecte[];

    attaques: IAttaque[];

    agriculteurs: IAgriculteur[];
    dateDAjout: string;
    dateValidation: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected imageService: ImageService,
        protected insecteService: InsecteService,
        protected attaqueService: AttaqueService,
        protected agriculteurService: AgriculteurService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ image }) => {
            this.image = image;
            this.dateDAjout = this.image.dateDAjout != null ? this.image.dateDAjout.format(DATE_TIME_FORMAT) : null;
            this.dateValidation = this.image.dateValidation != null ? this.image.dateValidation.format(DATE_TIME_FORMAT) : null;
        });
        this.insecteService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IInsecte[]>) => mayBeOk.ok),
                map((response: HttpResponse<IInsecte[]>) => response.body)
            )
            .subscribe((res: IInsecte[]) => (this.insectes = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.attaqueService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IAttaque[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAttaque[]>) => response.body)
            )
            .subscribe((res: IAttaque[]) => (this.attaques = res), (res: HttpErrorResponse) => this.onError(res.message));
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
        this.image.dateDAjout = this.dateDAjout != null ? moment(this.dateDAjout, DATE_TIME_FORMAT) : null;
        this.image.dateValidation = this.dateValidation != null ? moment(this.dateValidation, DATE_TIME_FORMAT) : null;
        if (this.image.id !== undefined) {
            this.subscribeToSaveResponse(this.imageService.update(this.image));
        } else {
            this.subscribeToSaveResponse(this.imageService.create(this.image));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IImage>>) {
        result.subscribe((res: HttpResponse<IImage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackInsecteById(index: number, item: IInsecte) {
        return item.id;
    }

    trackAttaqueById(index: number, item: IAttaque) {
        return item.id;
    }

    trackAgriculteurById(index: number, item: IAgriculteur) {
        return item.id;
    }
}
