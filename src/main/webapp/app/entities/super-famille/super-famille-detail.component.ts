import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISuperFamille } from 'app/shared/model/super-famille.model';

@Component({
    selector: 'jhi-super-famille-detail',
    templateUrl: './super-famille-detail.component.html'
})
export class SuperFamilleDetailComponent implements OnInit {
    superFamille: ISuperFamille;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ superFamille }) => {
            this.superFamille = superFamille;
        });
    }

    previousState() {
        window.history.back();
    }
}
