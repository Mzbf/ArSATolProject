import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IHerbe } from 'app/shared/model/herbe.model';

@Component({
    selector: 'jhi-herbe-detail',
    templateUrl: './herbe-detail.component.html'
})
export class HerbeDetailComponent implements OnInit {
    herbe: IHerbe;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ herbe }) => {
            this.herbe = herbe;
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
