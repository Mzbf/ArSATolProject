import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IAdministrateur } from 'app/shared/model/administrateur.model';
import { AdministrateurService } from './administrateur.service';

@Component({
    selector: 'jhi-administrateur-update',
    templateUrl: './administrateur-update.component.html'
})
export class AdministrateurUpdateComponent implements OnInit {
    administrateur: IAdministrateur;
    isSaving: boolean;

    constructor(protected administrateurService: AdministrateurService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ administrateur }) => {
            this.administrateur = administrateur;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.administrateur.id !== undefined) {
            this.subscribeToSaveResponse(this.administrateurService.update(this.administrateur));
        } else {
            this.subscribeToSaveResponse(this.administrateurService.create(this.administrateur));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdministrateur>>) {
        result.subscribe((res: HttpResponse<IAdministrateur>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
