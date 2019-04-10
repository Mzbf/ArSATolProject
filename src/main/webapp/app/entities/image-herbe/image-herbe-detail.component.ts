import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IImageHerbe } from 'app/shared/model/image-herbe.model';

@Component({
    selector: 'jhi-image-herbe-detail',
    templateUrl: './image-herbe-detail.component.html'
})
export class ImageHerbeDetailComponent implements OnInit {
    imageHerbe: IImageHerbe;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ imageHerbe }) => {
            this.imageHerbe = imageHerbe;
        });
    }

    previousState() {
        window.history.back();
    }
}
