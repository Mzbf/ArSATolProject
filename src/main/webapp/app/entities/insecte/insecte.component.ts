import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IInsecte } from 'app/shared/model/insecte.model';
import { AccountService } from 'app/core';
import { InsecteService } from './insecte.service';

@Component({
    selector: 'jhi-insecte',
    templateUrl: './insecte.component.html'
})
export class InsecteComponent implements OnInit, OnDestroy {
    insectes: IInsecte[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected insecteService: InsecteService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.insecteService
            .query()
            .pipe(
                filter((res: HttpResponse<IInsecte[]>) => res.ok),
                map((res: HttpResponse<IInsecte[]>) => res.body)
            )
            .subscribe(
                (res: IInsecte[]) => {
                    this.insectes = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInInsectes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IInsecte) {
        return item.id;
    }

    registerChangeInInsectes() {
        this.eventSubscriber = this.eventManager.subscribe('insecteListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
