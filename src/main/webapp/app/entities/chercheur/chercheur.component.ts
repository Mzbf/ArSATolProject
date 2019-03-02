import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IChercheur } from 'app/shared/model/chercheur.model';
import { AccountService } from 'app/core';
import { ChercheurService } from './chercheur.service';

@Component({
    selector: 'jhi-chercheur',
    templateUrl: './chercheur.component.html'
})
export class ChercheurComponent implements OnInit, OnDestroy {
    chercheurs: IChercheur[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected chercheurService: ChercheurService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.chercheurService
            .query()
            .pipe(
                filter((res: HttpResponse<IChercheur[]>) => res.ok),
                map((res: HttpResponse<IChercheur[]>) => res.body)
            )
            .subscribe(
                (res: IChercheur[]) => {
                    this.chercheurs = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInChercheurs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IChercheur) {
        return item.id;
    }

    registerChangeInChercheurs() {
        this.eventSubscriber = this.eventManager.subscribe('chercheurListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
