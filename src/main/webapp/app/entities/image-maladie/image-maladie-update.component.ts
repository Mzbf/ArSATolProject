import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IImageMaladie } from 'app/shared/model/image-maladie.model';
import { ImageMaladieService } from './image-maladie.service';
import { IMaladie } from 'app/shared/model/maladie.model';
import { MaladieService } from 'app/entities/maladie';

@Component({
    selector: 'jhi-image-maladie-update',
    templateUrl: './image-maladie-update.component.html'
})
export class ImageMaladieUpdateComponent implements OnInit {
    imageMaladie: IImageMaladie;
    isSaving: boolean;

    maladies: IMaladie[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected imageMaladieService: ImageMaladieService,
        protected maladieService: MaladieService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ imageMaladie }) => {
            this.imageMaladie = imageMaladie;
        });
        this.maladieService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IMaladie[]>) => mayBeOk.ok),
                map((response: HttpResponse<IMaladie[]>) => response.body)
            )
            .subscribe((res: IMaladie[]) => (this.maladies = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.imageMaladie.id !== undefined) {
            this.subscribeToSaveResponse(this.imageMaladieService.update(this.imageMaladie));
        } else {
            this.subscribeToSaveResponse(this.imageMaladieService.create(this.imageMaladie));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IImageMaladie>>) {
        result.subscribe((res: HttpResponse<IImageMaladie>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackMaladieById(index: number, item: IMaladie) {
        return item.id;
    }
}
