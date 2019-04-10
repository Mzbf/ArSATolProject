import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IInsecteRavageur } from 'app/shared/model/insecte-ravageur.model';
import { AccountService } from 'app/core';
import { InsecteRavageurService } from './insecte-ravageur.service';

@Component({
    selector: 'jhi-insecte-ravageur',
    templateUrl: './insecte-ravageur.component.html'
})
export class InsecteRavageurComponent implements OnInit, OnDestroy {
    insecteRavageurs: IInsecteRavageur[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected insecteRavageurService: InsecteRavageurService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.insecteRavageurService
            .query()
            .pipe(
                filter((res: HttpResponse<IInsecteRavageur[]>) => res.ok),
                map((res: HttpResponse<IInsecteRavageur[]>) => res.body)
            )
            .subscribe(
                (res: IInsecteRavageur[]) => {
                    this.insecteRavageurs = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInInsecteRavageurs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IInsecteRavageur) {
        return item.id;
    }

    registerChangeInInsecteRavageurs() {
        this.eventSubscriber = this.eventManager.subscribe('insecteRavageurListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
