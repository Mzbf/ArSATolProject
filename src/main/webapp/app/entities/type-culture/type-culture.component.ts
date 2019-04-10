import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITypeCulture } from 'app/shared/model/type-culture.model';
import { AccountService } from 'app/core';
import { TypeCultureService } from './type-culture.service';

@Component({
    selector: 'jhi-type-culture',
    templateUrl: './type-culture.component.html'
})
export class TypeCultureComponent implements OnInit, OnDestroy {
    typeCultures: ITypeCulture[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected typeCultureService: TypeCultureService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.typeCultureService
            .query()
            .pipe(
                filter((res: HttpResponse<ITypeCulture[]>) => res.ok),
                map((res: HttpResponse<ITypeCulture[]>) => res.body)
            )
            .subscribe(
                (res: ITypeCulture[]) => {
                    this.typeCultures = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTypeCultures();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITypeCulture) {
        return item.id;
    }

    registerChangeInTypeCultures() {
        this.eventSubscriber = this.eventManager.subscribe('typeCultureListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
