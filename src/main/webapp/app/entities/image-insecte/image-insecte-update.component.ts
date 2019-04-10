import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IImageInsecte } from 'app/shared/model/image-insecte.model';
import { ImageInsecteService } from './image-insecte.service';
import { IInsecte } from 'app/shared/model/insecte.model';
import { InsecteService } from 'app/entities/insecte';
import { FormGroup } from '@angular/forms';

@Component({
    selector: 'jhi-image-insecte-update',
    templateUrl: './image-insecte-update.component.html'
})
export class ImageInsecteUpdateComponent implements OnInit {
    imageInsecte: IImageInsecte;
    isSaving: boolean;
    public image: any = File;
    insectes: IInsecte[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected imageInsecteService: ImageInsecteService,
        protected insecteService: InsecteService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ imageInsecte }) => {
            this.imageInsecte = imageInsecte;
        });
        this.insecteService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IInsecte[]>) => mayBeOk.ok),
                map((response: HttpResponse<IInsecte[]>) => response.body)
            )
            .subscribe((res: IInsecte[]) => (this.insectes = res), (res: HttpErrorResponse) => this.onError(res.message));
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
            const fam = this.imageInsecte;
            const formData = new FormData();
            formData.append('insecte', JSON.stringify(this.imageInsecte));
            formData.append('file', this.image);
            if (this.imageInsecte.id !== undefined) {
                this.subscribeToSaveResponse(this.imageInsecteService.saveImage(formData));
            } else {
                this.subscribeToSaveResponse(this.imageInsecteService.saveImage(formData));
            }
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IImageInsecte>>) {
        result.subscribe((res: HttpResponse<IImageInsecte>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackInsecteById(index: number, item: IInsecte) {
        return item.id;
    }
}
