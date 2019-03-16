import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMethodeLutte } from 'app/shared/model/methode-lutte.model';

@Component({
    selector: 'jhi-methode-lutte-detail',
    templateUrl: './methode-lutte-detail.component.html'
})
export class MethodeLutteDetailComponent implements OnInit {
    methodeLutte: IMethodeLutte;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ methodeLutte }) => {
            this.methodeLutte = methodeLutte;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
