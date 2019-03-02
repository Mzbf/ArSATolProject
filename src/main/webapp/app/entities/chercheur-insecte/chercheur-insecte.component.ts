import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IChercheurInsecte } from 'app/shared/model/chercheur-insecte.model';
import { AccountService } from 'app/core';
import { ChercheurInsecteService } from './chercheur-insecte.service';

@Component({
    selector: 'jhi-chercheur-insecte',
    templateUrl: './chercheur-insecte.component.html'
})
export class ChercheurInsecteComponent implements OnInit, OnDestroy {
    chercheurInsectes: IChercheurInsecte[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected chercheurInsecteService: ChercheurInsecteService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.chercheurInsecteService
            .query()
            .pipe(
                filter((res: HttpResponse<IChercheurInsecte[]>) => res.ok),
                map((res: HttpResponse<IChercheurInsecte[]>) => res.body)
            )
            .subscribe(
                (res: IChercheurInsecte[]) => {
                    this.chercheurInsectes = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInChercheurInsectes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IChercheurInsecte) {
        return item.id;
    }

    registerChangeInChercheurInsectes() {
        this.eventSubscriber = this.eventManager.subscribe('chercheurInsecteListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
