import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IMaladie } from 'app/shared/model/maladie.model';
import { AccountService } from 'app/core';
import { MaladieService } from './maladie.service';

@Component({
    selector: 'jhi-maladie',
    templateUrl: './maladie.component.html'
})
export class MaladieComponent implements OnInit, OnDestroy {
    maladies: IMaladie[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected maladieService: MaladieService,
        protected jhiAlertService: JhiAlertService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.maladieService
            .query()
            .pipe(
                filter((res: HttpResponse<IMaladie[]>) => res.ok),
                map((res: HttpResponse<IMaladie[]>) => res.body)
            )
            .subscribe(
                (res: IMaladie[]) => {
                    this.maladies = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMaladies();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMaladie) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInMaladies() {
        this.eventSubscriber = this.eventManager.subscribe('maladieListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
