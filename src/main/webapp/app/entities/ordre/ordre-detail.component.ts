import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IOrdre } from 'app/shared/model/ordre.model';

@Component({
    selector: 'jhi-ordre-detail',
    templateUrl: './ordre-detail.component.html'
})
export class OrdreDetailComponent implements OnInit {
    ordre: IOrdre;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ordre }) => {
            this.ordre = ordre;
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
