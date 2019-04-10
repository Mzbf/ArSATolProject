import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IHerbe } from 'app/shared/model/herbe.model';
import { AccountService } from 'app/core';
import { HerbeService } from './herbe.service';

@Component({
    selector: 'jhi-herbe',
    templateUrl: './herbe.component.html'
})
export class HerbeComponent implements OnInit, OnDestroy {
    herbes: IHerbe[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected herbeService: HerbeService,
        protected jhiAlertService: JhiAlertService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.herbeService
            .query()
            .pipe(
                filter((res: HttpResponse<IHerbe[]>) => res.ok),
                map((res: HttpResponse<IHerbe[]>) => res.body)
            )
            .subscribe(
                (res: IHerbe[]) => {
                    this.herbes = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInHerbes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IHerbe) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInHerbes() {
        this.eventSubscriber = this.eventManager.subscribe('herbeListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
