import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IImageEnvoye } from 'app/shared/model/image-envoye.model';

@Component({
    selector: 'jhi-image-envoye-detail',
    templateUrl: './image-envoye-detail.component.html'
})
export class ImageEnvoyeDetailComponent implements OnInit {
    imageEnvoye: IImageEnvoye;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ imageEnvoye }) => {
            this.imageEnvoye = imageEnvoye;
        });
    }

    previousState() {
        window.history.back();
    }
}
