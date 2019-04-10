import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IInsecteUtile } from 'app/shared/model/insecte-utile.model';
import { InsecteUtileService } from './insecte-utile.service';
import { IInsecte } from 'app/shared/model/insecte.model';
import { InsecteService } from 'app/entities/insecte';
import { ICulture } from 'app/shared/model/culture.model';
import { CultureService } from 'app/entities/culture';
import { IChercheur } from 'app/shared/model/chercheur.model';
import { ChercheurService } from 'app/entities/chercheur';
import { IFamille } from 'app/shared/model/famille.model';
import { FamilleService } from 'app/entities/famille';

@Component({
    selector: 'jhi-insecte-utile-update',
    templateUrl: './insecte-utile-update.component.html'
})
export class InsecteUtileUpdateComponent implements OnInit {
    insecteUtile: IInsecteUtile;
    isSaving: boolean;

    typeutils: IInsecte[];

    cultures: ICulture[];

    chercheurs: IChercheur[];

    familles: IFamille[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected insecteUtileService: InsecteUtileService,
        protected insecteService: InsecteService,
        protected cultureService: CultureService,
        protected chercheurService: ChercheurService,
        protected familleService: FamilleService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ insecteUtile }) => {
            this.insecteUtile = insecteUtile;
        });
        this.insecteService
            .query({ filter: 'insecteutile-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IInsecte[]>) => mayBeOk.ok),
                map((response: HttpResponse<IInsecte[]>) => response.body)
            )
            .subscribe(
                (res: IInsecte[]) => {
                    if (!this.insecteUtile.typeUtilId) {
                        this.typeutils = res;
                    } else {
                        this.insecteService
                            .find(this.insecteUtile.typeUtilId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IInsecte>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IInsecte>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IInsecte) => (this.typeutils = [subRes].concat(res)),
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
        this.chercheurService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IChercheur[]>) => mayBeOk.ok),
                map((response: HttpResponse<IChercheur[]>) => response.body)
            )
            .subscribe((res: IChercheur[]) => (this.chercheurs = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.familleService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IFamille[]>) => mayBeOk.ok),
                map((response: HttpResponse<IFamille[]>) => response.body)
            )
            .subscribe((res: IFamille[]) => (this.familles = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.insecteUtile.id !== undefined) {
            this.subscribeToSaveResponse(this.insecteUtileService.update(this.insecteUtile));
        } else {
            this.subscribeToSaveResponse(this.insecteUtileService.create(this.insecteUtile));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IInsecteUtile>>) {
        result.subscribe((res: HttpResponse<IInsecteUtile>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackFamilleById(index: number, item: IFamille) {
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
