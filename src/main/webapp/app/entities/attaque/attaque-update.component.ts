import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IAttaque } from 'app/shared/model/attaque.model';
import { AttaqueService } from './attaque.service';
import { IInsecte } from 'app/shared/model/insecte.model';
import { InsecteService } from 'app/entities/insecte';
import { ICulture } from 'app/shared/model/culture.model';
import { CultureService } from 'app/entities/culture';
import { IChercheur } from 'app/shared/model/chercheur.model';
import { ChercheurService } from 'app/entities/chercheur';
import { IAdministrateur } from 'app/shared/model/administrateur.model';
import { AdministrateurService } from 'app/entities/administrateur';

@Component({
    selector: 'jhi-attaque-update',
    templateUrl: './attaque-update.component.html'
})
export class AttaqueUpdateComponent implements OnInit {
    attaque: IAttaque;
    isSaving: boolean;

    insectes: IInsecte[];

    cultures: ICulture[];

    chercheurs: IChercheur[];

    administrateurs: IAdministrateur[];
    dateValidation: string;
    dateAjout: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected attaqueService: AttaqueService,
        protected insecteService: InsecteService,
        protected cultureService: CultureService,
        protected chercheurService: ChercheurService,
        protected administrateurService: AdministrateurService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ attaque }) => {
            this.attaque = attaque;
            this.dateValidation = this.attaque.dateValidation != null ? this.attaque.dateValidation.format(DATE_TIME_FORMAT) : null;
            this.dateAjout = this.attaque.dateAjout != null ? this.attaque.dateAjout.format(DATE_TIME_FORMAT) : null;
        });
        this.insecteService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IInsecte[]>) => mayBeOk.ok),
                map((response: HttpResponse<IInsecte[]>) => response.body)
            )
            .subscribe((res: IInsecte[]) => (this.insectes = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.cultureService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICulture[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICulture[]>) => response.body)
            )
            .subscribe((res: ICulture[]) => (this.cultures = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.chercheurService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IChercheur[]>) => mayBeOk.ok),
                map((response: HttpResponse<IChercheur[]>) => response.body)
            )
            .subscribe((res: IChercheur[]) => (this.chercheurs = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.administrateurService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IAdministrateur[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAdministrateur[]>) => response.body)
            )
            .subscribe((res: IAdministrateur[]) => (this.administrateurs = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.attaque.dateValidation = this.dateValidation != null ? moment(this.dateValidation, DATE_TIME_FORMAT) : null;
        this.attaque.dateAjout = this.dateAjout != null ? moment(this.dateAjout, DATE_TIME_FORMAT) : null;
        if (this.attaque.id !== undefined) {
            this.subscribeToSaveResponse(this.attaqueService.update(this.attaque));
        } else {
            this.subscribeToSaveResponse(this.attaqueService.create(this.attaque));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAttaque>>) {
        result.subscribe((res: HttpResponse<IAttaque>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCultureById(index: number, item: ICulture) {
        return item.id;
    }

    trackChercheurById(index: number, item: IChercheur) {
        return item.id;
    }

    trackAdministrateurById(index: number, item: IAdministrateur) {
        return item.id;
    }
}
