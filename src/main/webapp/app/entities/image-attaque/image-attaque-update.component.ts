import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IImageAttaque } from 'app/shared/model/image-attaque.model';
import { ImageAttaqueService } from './image-attaque.service';
import { IAttaque } from 'app/shared/model/attaque.model';
import { AttaqueService } from 'app/entities/attaque';
import { FormGroup } from '@angular/forms';

@Component({
    selector: 'jhi-image-attaque-update',
    templateUrl: './image-attaque-update.component.html'
})
export class ImageAttaqueUpdateComponent implements OnInit {
    imageAttaque: IImageAttaque;
    isSaving: boolean;
    public image: any = File;
    attaques: IAttaque[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected imageAttaqueService: ImageAttaqueService,
        protected attaqueService: AttaqueService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ imageAttaque }) => {
            this.imageAttaque = imageAttaque;
        });
        this.attaqueService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IAttaque[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAttaque[]>) => response.body)
            )
            .subscribe((res: IAttaque[]) => (this.attaques = res), (res: HttpErrorResponse) => this.onError(res.message));
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
            const fam = this.imageAttaque;
            const formData = new FormData();
            formData.append('attaque', JSON.stringify(this.imageAttaque));
            formData.append('file', this.image);
            if (this.imageAttaque.id !== undefined) {
                this.subscribeToSaveResponse(this.imageAttaqueService.saveImage(formData));
            } else {
                this.subscribeToSaveResponse(this.imageAttaqueService.saveImage(formData));
            }
        }
    }

    /*  save() {
        this.isSaving = true;
        if (this.imageAttaque.id !== undefined) {
            this.subscribeToSaveResponse(this.imageAttaqueService.update(this.imageAttaque));
        } else {
            this.subscribeToSaveResponse(this.imageAttaqueService.create(this.imageAttaque));
        }
    }*/

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IImageAttaque>>) {
        result.subscribe((res: HttpResponse<IImageAttaque>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAttaqueById(index: number, item: IAttaque) {
        return item.id;
    }
}
