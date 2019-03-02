import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IChercheur } from 'app/shared/model/chercheur.model';
import { ChercheurService } from './chercheur.service';

@Component({
    selector: 'jhi-chercheur-update',
    templateUrl: './chercheur-update.component.html'
})
export class ChercheurUpdateComponent implements OnInit {
    chercheur: IChercheur;
    isSaving: boolean;

    constructor(protected chercheurService: ChercheurService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ chercheur }) => {
            this.chercheur = chercheur;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.chercheur.id !== undefined) {
            this.subscribeToSaveResponse(this.chercheurService.update(this.chercheur));
        } else {
            this.subscribeToSaveResponse(this.chercheurService.create(this.chercheur));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IChercheur>>) {
        result.subscribe((res: HttpResponse<IChercheur>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
