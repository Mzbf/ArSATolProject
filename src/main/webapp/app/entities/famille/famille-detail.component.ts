import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IFamille } from 'app/shared/model/famille.model';

@Component({
    selector: 'jhi-famille-detail',
    templateUrl: './famille-detail.component.html'
})
export class FamilleDetailComponent implements OnInit {
    famille: IFamille;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ famille }) => {
            this.famille = famille;
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
