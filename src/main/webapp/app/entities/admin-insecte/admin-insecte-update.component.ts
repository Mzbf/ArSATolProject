import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IAdminInsecte } from 'app/shared/model/admin-insecte.model';
import { AdminInsecteService } from './admin-insecte.service';
import { IInsecte } from 'app/shared/model/insecte.model';
import { InsecteService } from 'app/entities/insecte';
import { IAdministrateur } from 'app/shared/model/administrateur.model';
import { AdministrateurService } from 'app/entities/administrateur';

@Component({
    selector: 'jhi-admin-insecte-update',
    templateUrl: './admin-insecte-update.component.html'
})
export class AdminInsecteUpdateComponent implements OnInit {
    adminInsecte: IAdminInsecte;
    isSaving: boolean;

    insectes: IInsecte[];

    administrateurs: IAdministrateur[];
    dateDAjout: string;
    dateValidation: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected adminInsecteService: AdminInsecteService,
        protected insecteService: InsecteService,
        protected administrateurService: AdministrateurService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ adminInsecte }) => {
            this.adminInsecte = adminInsecte;
            this.dateDAjout = this.adminInsecte.dateDAjout != null ? this.adminInsecte.dateDAjout.format(DATE_TIME_FORMAT) : null;
            this.dateValidation =
                this.adminInsecte.dateValidation != null ? this.adminInsecte.dateValidation.format(DATE_TIME_FORMAT) : null;
        });
        this.insecteService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IInsecte[]>) => mayBeOk.ok),
                map((response: HttpResponse<IInsecte[]>) => response.body)
            )
            .subscribe((res: IInsecte[]) => (this.insectes = res), (res: HttpErrorResponse) => this.onError(res.message));
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
        this.adminInsecte.dateDAjout = this.dateDAjout != null ? moment(this.dateDAjout, DATE_TIME_FORMAT) : null;
        this.adminInsecte.dateValidation = this.dateValidation != null ? moment(this.dateValidation, DATE_TIME_FORMAT) : null;
        if (this.adminInsecte.id !== undefined) {
            this.subscribeToSaveResponse(this.adminInsecteService.update(this.adminInsecte));
        } else {
            this.subscribeToSaveResponse(this.adminInsecteService.create(this.adminInsecte));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdminInsecte>>) {
        result.subscribe((res: HttpResponse<IAdminInsecte>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAdministrateurById(index: number, item: IAdministrateur) {
        return item.id;
    }
}
