import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IImageCulture } from 'app/shared/model/image-culture.model';

@Component({
    selector: 'jhi-image-culture-detail',
    templateUrl: './image-culture-detail.component.html'
})
export class ImageCultureDetailComponent implements OnInit {
    imageCulture: IImageCulture;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ imageCulture }) => {
            this.imageCulture = imageCulture;
        });
    }

    previousState() {
        window.history.back();
    }
}
