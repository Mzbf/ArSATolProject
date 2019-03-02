import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAdministrateur } from 'app/shared/model/administrateur.model';
import { AccountService } from 'app/core';
import { AdministrateurService } from './administrateur.service';

@Component({
    selector: 'jhi-administrateur',
    templateUrl: './administrateur.component.html'
})
export class AdministrateurComponent implements OnInit, OnDestroy {
    administrateurs: IAdministrateur[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected administrateurService: AdministrateurService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.administrateurService
            .query()
            .pipe(
                filter((res: HttpResponse<IAdministrateur[]>) => res.ok),
                map((res: HttpResponse<IAdministrateur[]>) => res.body)
            )
            .subscribe(
                (res: IAdministrateur[]) => {
                    this.administrateurs = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAdministrateurs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAdministrateur) {
        return item.id;
    }

    registerChangeInAdministrateurs() {
        this.eventSubscriber = this.eventManager.subscribe('administrateurListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
