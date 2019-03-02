import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ISuperFamille } from 'app/shared/model/super-famille.model';
import { SuperFamilleService } from './super-famille.service';

@Component({
    selector: 'jhi-super-famille-update',
    templateUrl: './super-famille-update.component.html'
})
export class SuperFamilleUpdateComponent implements OnInit {
    superFamille: ISuperFamille;
    isSaving: boolean;

    constructor(protected superFamilleService: SuperFamilleService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ superFamille }) => {
            this.superFamille = superFamille;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.superFamille.id !== undefined) {
            this.subscribeToSaveResponse(this.superFamilleService.update(this.superFamille));
        } else {
            this.subscribeToSaveResponse(this.superFamilleService.create(this.superFamille));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISuperFamille>>) {
        result.subscribe((res: HttpResponse<ISuperFamille>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
