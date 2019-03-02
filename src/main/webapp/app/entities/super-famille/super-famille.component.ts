import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISuperFamille } from 'app/shared/model/super-famille.model';
import { AccountService } from 'app/core';
import { SuperFamilleService } from './super-famille.service';

@Component({
    selector: 'jhi-super-famille',
    templateUrl: './super-famille.component.html'
})
export class SuperFamilleComponent implements OnInit, OnDestroy {
    superFamilles: ISuperFamille[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected superFamilleService: SuperFamilleService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.superFamilleService
            .query()
            .pipe(
                filter((res: HttpResponse<ISuperFamille[]>) => res.ok),
                map((res: HttpResponse<ISuperFamille[]>) => res.body)
            )
            .subscribe(
                (res: ISuperFamille[]) => {
                    this.superFamilles = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSuperFamilles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISuperFamille) {
        return item.id;
    }

    registerChangeInSuperFamilles() {
        this.eventSubscriber = this.eventManager.subscribe('superFamilleListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
