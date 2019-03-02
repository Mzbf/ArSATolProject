import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IAgriculteur } from 'app/shared/model/agriculteur.model';
import { AgriculteurService } from './agriculteur.service';

@Component({
    selector: 'jhi-agriculteur-update',
    templateUrl: './agriculteur-update.component.html'
})
export class AgriculteurUpdateComponent implements OnInit {
    agriculteur: IAgriculteur;
    isSaving: boolean;

    constructor(protected agriculteurService: AgriculteurService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ agriculteur }) => {
            this.agriculteur = agriculteur;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.agriculteur.id !== undefined) {
            this.subscribeToSaveResponse(this.agriculteurService.update(this.agriculteur));
        } else {
            this.subscribeToSaveResponse(this.agriculteurService.create(this.agriculteur));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAgriculteur>>) {
        result.subscribe((res: HttpResponse<IAgriculteur>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
