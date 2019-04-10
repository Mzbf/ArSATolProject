import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IAttaque } from 'app/shared/model/attaque.model';

@Component({
    selector: 'jhi-attaque-detail',
    templateUrl: './attaque-detail.component.html'
})
export class AttaqueDetailComponent implements OnInit {
    attaque: IAttaque;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ attaque }) => {
            this.attaque = attaque;
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
