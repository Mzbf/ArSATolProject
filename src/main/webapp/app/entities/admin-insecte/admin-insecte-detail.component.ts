import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdminInsecte } from 'app/shared/model/admin-insecte.model';

@Component({
    selector: 'jhi-admin-insecte-detail',
    templateUrl: './admin-insecte-detail.component.html'
})
export class AdminInsecteDetailComponent implements OnInit {
    adminInsecte: IAdminInsecte;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ adminInsecte }) => {
            this.adminInsecte = adminInsecte;
        });
    }

    previousState() {
        window.history.back();
    }
}
