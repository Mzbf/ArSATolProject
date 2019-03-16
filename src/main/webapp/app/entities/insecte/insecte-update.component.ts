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
import { IMethodeLutte } from 'app/shared/model/methode-lutte.model';
import { MethodeLutteService } from 'app/entities/methode-lutte';
import { IChercheur } from 'app/shared/model/chercheur.model';
import { ChercheurService } from 'app/entities/chercheur';
import { IAdministrateur } from 'app/shared/model/administrateur.model';
import { AdministrateurService } from 'app/entities/administrateur';

@Component({
    selector: 'jhi-insecte-update',
    templateUrl: './insecte-update.component.html'
})
export class InsecteUpdateComponent implements OnInit {
    insecte: IInsecte;
    isSaving: boolean;

    methodes: IMethodeLutte[];

    chercheurs: IChercheur[];

    administrateurs: IAdministrateur[];
    dateValidation: string;
    dateAjout: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected insecteService: InsecteService,
        protected methodeLutteService: MethodeLutteService,
        protected chercheurService: ChercheurService,
        protected administrateurService: AdministrateurService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ insecte }) => {
            this.insecte = insecte;
            this.dateValidation = this.insecte.dateValidation != null ? this.insecte.dateValidation.format(DATE_TIME_FORMAT) : null;
            this.dateAjout = this.insecte.dateAjout != null ? this.insecte.dateAjout.format(DATE_TIME_FORMAT) : null;
        });
        this.methodeLutteService
            .query({ filter: 'insecte-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IMethodeLutte[]>) => mayBeOk.ok),
                map((response: HttpResponse<IMethodeLutte[]>) => response.body)
            )
            .subscribe(
                (res: IMethodeLutte[]) => {
                    if (!this.insecte.methode || !this.insecte.methode.id) {
                        this.methodes = res;
                    } else {
                        this.methodeLutteService
                            .find(this.insecte.methode.id)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IMethodeLutte>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IMethodeLutte>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IMethodeLutte) => (this.methodes = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
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

    trackMethodeLutteById(index: number, item: IMethodeLutte) {
        return item.id;
    }

    trackChercheurById(index: number, item: IChercheur) {
        return item.id;
    }

    trackAdministrateurById(index: number, item: IAdministrateur) {
        return item.id;
    }
}
