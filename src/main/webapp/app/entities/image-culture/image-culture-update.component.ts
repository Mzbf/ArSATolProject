import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IImageCulture } from 'app/shared/model/image-culture.model';
import { ImageCultureService } from './image-culture.service';
import { ICulture } from 'app/shared/model/culture.model';
import { CultureService } from 'app/entities/culture';
import { FormGroup } from '@angular/forms';

@Component({
    selector: 'jhi-image-culture-update',
    templateUrl: './image-culture-update.component.html'
})
export class ImageCultureUpdateComponent implements OnInit {
    imageCulture: IImageCulture;
    isSaving: boolean;
    public image: any = File;
    cultures: ICulture[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected imageCultureService: ImageCultureService,
        protected cultureService: CultureService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ imageCulture }) => {
            this.imageCulture = imageCulture;
        });
        this.cultureService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICulture[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICulture[]>) => response.body)
            )
            .subscribe((res: ICulture[]) => (this.cultures = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    onFileChange(event) {
        const files = event.target.files[0];
        this.image = files;
    }

    save(editForm: FormGroup) {
        this.isSaving = true;
        if (editForm.valid) {
            const fam = this.imageCulture;
            const formData = new FormData();
            formData.append('culture', JSON.stringify(this.imageCulture));
            formData.append('file', this.image);
            if (this.imageCulture.id !== undefined) {
                this.subscribeToSaveResponse(this.imageCultureService.saveImage(formData));
            } else {
                this.subscribeToSaveResponse(this.imageCultureService.saveImage(formData));
            }
        }
    }
    /*  save() {
        this.isSaving = true;
        if (this.imageCulture.id !== undefined) {
            this.subscribeToSaveResponse(this.imageCultureService.update(this.imageCulture));
        } else {
            this.subscribeToSaveResponse(this.imageCultureService.create(this.imageCulture));
        }
    }*/

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IImageCulture>>) {
        result.subscribe((res: HttpResponse<IImageCulture>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCultureById(index: number, item: ICulture) {
        return item.id;
    }
}
