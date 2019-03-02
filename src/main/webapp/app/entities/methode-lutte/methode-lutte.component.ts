import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMethodeLutte } from 'app/shared/model/methode-lutte.model';
import { AccountService } from 'app/core';
import { MethodeLutteService } from './methode-lutte.service';

@Component({
    selector: 'jhi-methode-lutte',
    templateUrl: './methode-lutte.component.html'
})
export class MethodeLutteComponent implements OnInit, OnDestroy {
    methodeLuttes: IMethodeLutte[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected methodeLutteService: MethodeLutteService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.methodeLutteService
            .query()
            .pipe(
                filter((res: HttpResponse<IMethodeLutte[]>) => res.ok),
                map((res: HttpResponse<IMethodeLutte[]>) => res.body)
            )
            .subscribe(
                (res: IMethodeLutte[]) => {
                    this.methodeLuttes = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMethodeLuttes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMethodeLutte) {
        return item.id;
    }

    registerChangeInMethodeLuttes() {
        this.eventSubscriber = this.eventManager.subscribe('methodeLutteListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
