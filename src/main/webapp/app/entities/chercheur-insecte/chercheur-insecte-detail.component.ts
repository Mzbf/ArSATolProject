import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IChercheurInsecte } from 'app/shared/model/chercheur-insecte.model';

@Component({
    selector: 'jhi-chercheur-insecte-detail',
    templateUrl: './chercheur-insecte-detail.component.html'
})
export class ChercheurInsecteDetailComponent implements OnInit {
    chercheurInsecte: IChercheurInsecte;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ chercheurInsecte }) => {
            this.chercheurInsecte = chercheurInsecte;
        });
    }

    previousState() {
        window.history.back();
    }
}
