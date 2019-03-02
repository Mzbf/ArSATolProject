import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMethodeLutte } from 'app/shared/model/methode-lutte.model';

@Component({
    selector: 'jhi-methode-lutte-detail',
    templateUrl: './methode-lutte-detail.component.html'
})
export class MethodeLutteDetailComponent implements OnInit {
    methodeLutte: IMethodeLutte;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ methodeLutte }) => {
            this.methodeLutte = methodeLutte;
        });
    }

    previousState() {
        window.history.back();
    }
}
