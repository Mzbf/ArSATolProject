import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAgriculteur } from 'app/shared/model/agriculteur.model';
import { AccountService } from 'app/core';
import { AgriculteurService } from './agriculteur.service';

@Component({
    selector: 'jhi-agriculteur',
    templateUrl: './agriculteur.component.html'
})
export class AgriculteurComponent implements OnInit, OnDestroy {
    agriculteurs: IAgriculteur[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected agriculteurService: AgriculteurService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.agriculteurService
            .query()
            .pipe(
                filter((res: HttpResponse<IAgriculteur[]>) => res.ok),
                map((res: HttpResponse<IAgriculteur[]>) => res.body)
            )
            .subscribe(
                (res: IAgriculteur[]) => {
                    this.agriculteurs = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAgriculteurs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAgriculteur) {
        return item.id;
    }

    registerChangeInAgriculteurs() {
        this.eventSubscriber = this.eventManager.subscribe('agriculteurListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
