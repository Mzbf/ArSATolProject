import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeInsecte } from 'app/shared/model/type-insecte.model';

@Component({
    selector: 'jhi-type-insecte-detail',
    templateUrl: './type-insecte-detail.component.html'
})
export class TypeInsecteDetailComponent implements OnInit {
    typeInsecte: ITypeInsecte;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeInsecte }) => {
            this.typeInsecte = typeInsecte;
        });
    }

    previousState() {
        window.history.back();
    }
}
