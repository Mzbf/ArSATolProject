import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAdminInsecte } from 'app/shared/model/admin-insecte.model';
import { AccountService } from 'app/core';
import { AdminInsecteService } from './admin-insecte.service';

@Component({
    selector: 'jhi-admin-insecte',
    templateUrl: './admin-insecte.component.html'
})
export class AdminInsecteComponent implements OnInit, OnDestroy {
    adminInsectes: IAdminInsecte[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected adminInsecteService: AdminInsecteService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.adminInsecteService
            .query()
            .pipe(
                filter((res: HttpResponse<IAdminInsecte[]>) => res.ok),
                map((res: HttpResponse<IAdminInsecte[]>) => res.body)
            )
            .subscribe(
                (res: IAdminInsecte[]) => {
                    this.adminInsectes = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAdminInsectes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAdminInsecte) {
        return item.id;
    }

    registerChangeInAdminInsectes() {
        this.eventSubscriber = this.eventManager.subscribe('adminInsecteListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
