import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAttaque } from 'app/shared/model/attaque.model';

@Component({
    selector: 'jhi-attaque-detail',
    templateUrl: './attaque-detail.component.html'
})
export class AttaqueDetailComponent implements OnInit {
    attaque: IAttaque;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ attaque }) => {
            this.attaque = attaque;
        });
    }

    previousState() {
        window.history.back();
    }
}
