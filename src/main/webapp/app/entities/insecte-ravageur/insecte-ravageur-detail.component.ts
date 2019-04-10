import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInsecteRavageur } from 'app/shared/model/insecte-ravageur.model';

@Component({
    selector: 'jhi-insecte-ravageur-detail',
    templateUrl: './insecte-ravageur-detail.component.html'
})
export class InsecteRavageurDetailComponent implements OnInit {
    insecteRavageur: IInsecteRavageur;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ insecteRavageur }) => {
            this.insecteRavageur = insecteRavageur;
        });
    }

    previousState() {
        window.history.back();
    }
}
