import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMaladie } from 'app/shared/model/maladie.model';

@Component({
    selector: 'jhi-maladie-detail',
    templateUrl: './maladie-detail.component.html'
})
export class MaladieDetailComponent implements OnInit {
    maladie: IMaladie;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ maladie }) => {
            this.maladie = maladie;
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
