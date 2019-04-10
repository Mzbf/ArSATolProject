import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeCulture } from 'app/shared/model/type-culture.model';

@Component({
    selector: 'jhi-type-culture-detail',
    templateUrl: './type-culture-detail.component.html'
})
export class TypeCultureDetailComponent implements OnInit {
    typeCulture: ITypeCulture;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeCulture }) => {
            this.typeCulture = typeCulture;
        });
    }

    previousState() {
        window.history.back();
    }
}
