import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IChercheur } from 'app/shared/model/chercheur.model';

@Component({
    selector: 'jhi-chercheur-detail',
    templateUrl: './chercheur-detail.component.html'
})
export class ChercheurDetailComponent implements OnInit {
    chercheur: IChercheur;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ chercheur }) => {
            this.chercheur = chercheur;
        });
    }

    previousState() {
        window.history.back();
    }
}
