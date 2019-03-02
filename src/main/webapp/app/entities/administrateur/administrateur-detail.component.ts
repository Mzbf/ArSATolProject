import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdministrateur } from 'app/shared/model/administrateur.model';

@Component({
    selector: 'jhi-administrateur-detail',
    templateUrl: './administrateur-detail.component.html'
})
export class AdministrateurDetailComponent implements OnInit {
    administrateur: IAdministrateur;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ administrateur }) => {
            this.administrateur = administrateur;
        });
    }

    previousState() {
        window.history.back();
    }
}
