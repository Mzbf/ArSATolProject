import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IInsecteUtile } from 'app/shared/model/insecte-utile.model';
import { AccountService } from 'app/core';
import { InsecteUtileService } from './insecte-utile.service';

@Component({
    selector: 'jhi-insecte-utile',
    templateUrl: './insecte-utile.component.html'
})
export class InsecteUtileComponent implements OnInit, OnDestroy {
    insecteUtiles: IInsecteUtile[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected insecteUtileService: InsecteUtileService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.insecteUtileService
            .query()
            .pipe(
                filter((res: HttpResponse<IInsecteUtile[]>) => res.ok),
                map((res: HttpResponse<IInsecteUtile[]>) => res.body)
            )
            .subscribe(
                (res: IInsecteUtile[]) => {
                    this.insecteUtiles = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInInsecteUtiles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IInsecteUtile) {
        return item.id;
    }

    registerChangeInInsecteUtiles() {
        this.eventSubscriber = this.eventManager.subscribe('insecteUtileListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
