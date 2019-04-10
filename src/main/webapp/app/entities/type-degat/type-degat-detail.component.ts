import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeDegat } from 'app/shared/model/type-degat.model';

@Component({
    selector: 'jhi-type-degat-detail',
    templateUrl: './type-degat-detail.component.html'
})
export class TypeDegatDetailComponent implements OnInit {
    typeDegat: ITypeDegat;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeDegat }) => {
            this.typeDegat = typeDegat;
        });
    }

    previousState() {
        window.history.back();
    }
}
