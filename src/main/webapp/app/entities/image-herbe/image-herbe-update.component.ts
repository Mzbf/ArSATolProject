import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IImageHerbe } from 'app/shared/model/image-herbe.model';
import { ImageHerbeService } from './image-herbe.service';
import { IHerbe } from 'app/shared/model/herbe.model';
import { HerbeService } from 'app/entities/herbe';

@Component({
    selector: 'jhi-image-herbe-update',
    templateUrl: './image-herbe-update.component.html'
})
export class ImageHerbeUpdateComponent implements OnInit {
    imageHerbe: IImageHerbe;
    isSaving: boolean;

    herbes: IHerbe[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected imageHerbeService: ImageHerbeService,
        protected herbeService: HerbeService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ imageHerbe }) => {
            this.imageHerbe = imageHerbe;
        });
        this.herbeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IHerbe[]>) => mayBeOk.ok),
                map((response: HttpResponse<IHerbe[]>) => response.body)
            )
            .subscribe((res: IHerbe[]) => (this.herbes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.imageHerbe.id !== undefined) {
            this.subscribeToSaveResponse(this.imageHerbeService.update(this.imageHerbe));
        } else {
            this.subscribeToSaveResponse(this.imageHerbeService.create(this.imageHerbe));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IImageHerbe>>) {
        result.subscribe((res: HttpResponse<IImageHerbe>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackHerbeById(index: number, item: IHerbe) {
        return item.id;
    }
}
