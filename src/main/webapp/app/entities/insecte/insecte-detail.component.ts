import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInsecte } from 'app/shared/model/insecte.model';

@Component({
    selector: 'jhi-insecte-detail',
    templateUrl: './insecte-detail.component.html'
})
export class InsecteDetailComponent implements OnInit {
    insecte: IInsecte;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ insecte }) => {
            this.insecte = insecte;
        });
    }

    previousState() {
        window.history.back();
    }
}
