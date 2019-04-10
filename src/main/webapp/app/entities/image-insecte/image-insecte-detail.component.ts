import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IImageInsecte } from 'app/shared/model/image-insecte.model';

@Component({
    selector: 'jhi-image-insecte-detail',
    templateUrl: './image-insecte-detail.component.html'
})
export class ImageInsecteDetailComponent implements OnInit {
    imageInsecte: IImageInsecte;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ imageInsecte }) => {
            this.imageInsecte = imageInsecte;
        });
    }

    previousState() {
        window.history.back();
    }
}
