import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IInsecteRavageur } from 'app/shared/model/insecte-ravageur.model';
import { InsecteRavageurService } from './insecte-ravageur.service';
import { IInsecte } from 'app/shared/model/insecte.model';
import { InsecteService } from 'app/entities/insecte';
import { IMethodeLutte } from 'app/shared/model/methode-lutte.model';
import { MethodeLutteService } from 'app/entities/methode-lutte';
import { ICulture } from 'app/shared/model/culture.model';
import { CultureService } from 'app/entities/culture';
import { IChercheur } from 'app/shared/model/chercheur.model';
import { ChercheurService } from 'app/entities/chercheur';
import { IFamille } from 'app/shared/model/famille.model';
import { FamilleService } from 'app/entities/famille';

@Component({
    selector: 'jhi-insecte-ravageur-update',
    templateUrl: './insecte-ravageur-update.component.html'
})
export class InsecteRavageurUpdateComponent implements OnInit {
    insecteRavageur: IInsecteRavageur;
    isSaving: boolean;

    typeravs: IInsecte[];

    ravageurmls: IMethodeLutte[];

    cultures: ICulture[];

    chercheurs: IChercheur[];

    familles: IFamille[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected insecteRavageurService: InsecteRavageurService,
        protected insecteService: InsecteService,
        protected methodeLutteService: MethodeLutteService,
        protected cultureService: CultureService,
        protected chercheurService: ChercheurService,
        protected familleService: FamilleService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ insecteRavageur }) => {
            this.insecteRavageur = insecteRavageur;
        });
        this.insecteService
            .query({ filter: 'insecteravageur-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IInsecte[]>) => mayBeOk.ok),
                map((response: HttpResponse<IInsecte[]>) => response.body)
            )
            .subscribe(
                (res: IInsecte[]) => {
                    if (!this.insecteRavageur.typeRavId) {
                        this.typeravs = res;
                    } else {
                        this.insecteService
                            .find(this.insecteRavageur.typeRavId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IInsecte>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IInsecte>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IInsecte) => (this.typeravs = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.methodeLutteService
            .query({ filter: 'insecteravageur-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IMethodeLutte[]>) => mayBeOk.ok),
                map((response: HttpResponse<IMethodeLutte[]>) => response.body)
            )
            .subscribe(
                (res: IMethodeLutte[]) => {
                    if (!this.insecteRavageur.ravageurMLId) {
                        this.ravageurmls = res;
                    } else {
                        this.methodeLutteService
                            .find(this.insecteRavageur.ravageurMLId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IMethodeLutte>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IMethodeLutte>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IMethodeLutte) => (this.ravageurmls = [subRes].concat(res)),
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
        if (this.insecteRavageur.id !== undefined) {
            this.subscribeToSaveResponse(this.insecteRavageurService.update(this.insecteRavageur));
        } else {
            this.subscribeToSaveResponse(this.insecteRavageurService.create(this.insecteRavageur));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IInsecteRavageur>>) {
        result.subscribe((res: HttpResponse<IInsecteRavageur>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackMethodeLutteById(index: number, item: IMethodeLutte) {
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
