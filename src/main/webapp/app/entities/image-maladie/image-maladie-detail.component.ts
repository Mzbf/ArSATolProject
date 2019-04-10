import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IImageMaladie } from 'app/shared/model/image-maladie.model';

@Component({
    selector: 'jhi-image-maladie-detail',
    templateUrl: './image-maladie-detail.component.html'
})
export class ImageMaladieDetailComponent implements OnInit {
    imageMaladie: IImageMaladie;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ imageMaladie }) => {
            this.imageMaladie = imageMaladie;
        });
    }

    previousState() {
        window.history.back();
    }
}
