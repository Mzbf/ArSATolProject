import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITypeDegat } from 'app/shared/model/type-degat.model';
import { AccountService } from 'app/core';
import { TypeDegatService } from './type-degat.service';

@Component({
    selector: 'jhi-type-degat',
    templateUrl: './type-degat.component.html'
})
export class TypeDegatComponent implements OnInit, OnDestroy {
    typeDegats: ITypeDegat[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected typeDegatService: TypeDegatService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.typeDegatService
            .query()
            .pipe(
                filter((res: HttpResponse<ITypeDegat[]>) => res.ok),
                map((res: HttpResponse<ITypeDegat[]>) => res.body)
            )
            .subscribe(
                (res: ITypeDegat[]) => {
                    this.typeDegats = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTypeDegats();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITypeDegat) {
        return item.id;
    }

    registerChangeInTypeDegats() {
        this.eventSubscriber = this.eventManager.subscribe('typeDegatListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
