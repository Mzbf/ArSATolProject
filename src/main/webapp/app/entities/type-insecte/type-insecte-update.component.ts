import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ITypeInsecte } from 'app/shared/model/type-insecte.model';
import { TypeInsecteService } from './type-insecte.service';

@Component({
    selector: 'jhi-type-insecte-update',
    templateUrl: './type-insecte-update.component.html'
})
export class TypeInsecteUpdateComponent implements OnInit {
    typeInsecte: ITypeInsecte;
    isSaving: boolean;

    constructor(protected typeInsecteService: TypeInsecteService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ typeInsecte }) => {
            this.typeInsecte = typeInsecte;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.typeInsecte.id !== undefined) {
            this.subscribeToSaveResponse(this.typeInsecteService.update(this.typeInsecte));
        } else {
            this.subscribeToSaveResponse(this.typeInsecteService.create(this.typeInsecte));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeInsecte>>) {
        result.subscribe((res: HttpResponse<ITypeInsecte>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
