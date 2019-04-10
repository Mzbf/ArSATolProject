import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAgriculteur } from 'app/shared/model/agriculteur.model';

@Component({
    selector: 'jhi-agriculteur-detail',
    templateUrl: './agriculteur-detail.component.html'
})
export class AgriculteurDetailComponent implements OnInit {
    agriculteur: IAgriculteur;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ agriculteur }) => {
            this.agriculteur = agriculteur;
        });
    }

    previousState() {
        window.history.back();
    }
}
