import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDemo } from 'app/shared/model/demo.model';
import { AccountService } from 'app/core';
import { DemoService } from './demo.service';

@Component({
    selector: 'jhi-demo',
    templateUrl: './demo.component.html'
})
export class DemoComponent implements OnInit, OnDestroy {
    demos: IDemo[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected demoService: DemoService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.demoService
            .query()
            .pipe(
                filter((res: HttpResponse<IDemo[]>) => res.ok),
                map((res: HttpResponse<IDemo[]>) => res.body)
            )
            .subscribe(
                (res: IDemo[]) => {
                    this.demos = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDemos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDemo) {
        return item.id;
    }

    registerChangeInDemos() {
        this.eventSubscriber = this.eventManager.subscribe('demoListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
