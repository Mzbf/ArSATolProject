import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ITypeCulture } from 'app/shared/model/type-culture.model';
import { TypeCultureService } from './type-culture.service';

@Component({
    selector: 'jhi-type-culture-update',
    templateUrl: './type-culture-update.component.html'
})
export class TypeCultureUpdateComponent implements OnInit {
    typeCulture: ITypeCulture;
    isSaving: boolean;

    constructor(protected typeCultureService: TypeCultureService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ typeCulture }) => {
            this.typeCulture = typeCulture;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.typeCulture.id !== undefined) {
            this.subscribeToSaveResponse(this.typeCultureService.update(this.typeCulture));
        } else {
            this.subscribeToSaveResponse(this.typeCultureService.create(this.typeCulture));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeCulture>>) {
        result.subscribe((res: HttpResponse<ITypeCulture>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
