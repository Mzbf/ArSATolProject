import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ITypeDegat } from 'app/shared/model/type-degat.model';
import { TypeDegatService } from './type-degat.service';

@Component({
    selector: 'jhi-type-degat-update',
    templateUrl: './type-degat-update.component.html'
})
export class TypeDegatUpdateComponent implements OnInit {
    typeDegat: ITypeDegat;
    isSaving: boolean;

    constructor(protected typeDegatService: TypeDegatService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ typeDegat }) => {
            this.typeDegat = typeDegat;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.typeDegat.id !== undefined) {
            this.subscribeToSaveResponse(this.typeDegatService.update(this.typeDegat));
        } else {
            this.subscribeToSaveResponse(this.typeDegatService.create(this.typeDegat));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeDegat>>) {
        result.subscribe((res: HttpResponse<ITypeDegat>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
