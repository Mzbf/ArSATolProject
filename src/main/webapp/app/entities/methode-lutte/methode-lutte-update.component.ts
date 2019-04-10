import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiDataUtils } from 'ng-jhipster';
import { IMethodeLutte } from 'app/shared/model/methode-lutte.model';
import { MethodeLutteService } from './methode-lutte.service';

@Component({
    selector: 'jhi-methode-lutte-update',
    templateUrl: './methode-lutte-update.component.html'
})
export class MethodeLutteUpdateComponent implements OnInit {
    methodeLutte: IMethodeLutte;
    isSaving: boolean;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected methodeLutteService: MethodeLutteService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ methodeLutte }) => {
            this.methodeLutte = methodeLutte;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.methodeLutte.id !== undefined) {
            this.subscribeToSaveResponse(this.methodeLutteService.update(this.methodeLutte));
        } else {
            this.subscribeToSaveResponse(this.methodeLutteService.create(this.methodeLutte));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMethodeLutte>>) {
        result.subscribe((res: HttpResponse<IMethodeLutte>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
