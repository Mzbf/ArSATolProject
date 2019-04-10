import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IImageAttaque } from 'app/shared/model/image-attaque.model';

@Component({
    selector: 'jhi-image-attaque-detail',
    templateUrl: './image-attaque-detail.component.html'
})
export class ImageAttaqueDetailComponent implements OnInit {
    imageAttaque: IImageAttaque;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ imageAttaque }) => {
            this.imageAttaque = imageAttaque;
        });
    }

    previousState() {
        window.history.back();
    }
}
