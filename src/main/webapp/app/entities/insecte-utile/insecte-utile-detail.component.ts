import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInsecteUtile } from 'app/shared/model/insecte-utile.model';

@Component({
    selector: 'jhi-insecte-utile-detail',
    templateUrl: './insecte-utile-detail.component.html'
})
export class InsecteUtileDetailComponent implements OnInit {
    insecteUtile: IInsecteUtile;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ insecteUtile }) => {
            this.insecteUtile = insecteUtile;
        });
    }

    previousState() {
        window.history.back();
    }
}
