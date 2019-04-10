import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IAttaque } from 'app/shared/model/attaque.model';
import { AccountService } from 'app/core';
import { AttaqueService } from './attaque.service';

@Component({
    selector: 'jhi-attaque',
    templateUrl: './attaque.component.html'
})
export class AttaqueComponent implements OnInit, OnDestroy {
    attaques: IAttaque[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected attaqueService: AttaqueService,
        protected jhiAlertService: JhiAlertService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.attaqueService
            .query()
            .pipe(
                filter((res: HttpResponse<IAttaque[]>) => res.ok),
                map((res: HttpResponse<IAttaque[]>) => res.body)
            )
            .subscribe(
                (res: IAttaque[]) => {
                    this.attaques = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAttaques();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAttaque) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInAttaques() {
        this.eventSubscriber = this.eventManager.subscribe('attaqueListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
