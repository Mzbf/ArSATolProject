import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IInsecte } from 'app/shared/model/insecte.model';

@Component({
    selector: 'jhi-insecte-detail',
    templateUrl: './insecte-detail.component.html'
})
export class InsecteDetailComponent implements OnInit {
    insecte: IInsecte;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ insecte }) => {
            this.insecte = insecte;
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
