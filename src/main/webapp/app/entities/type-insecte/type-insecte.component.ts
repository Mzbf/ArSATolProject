import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITypeInsecte } from 'app/shared/model/type-insecte.model';
import { AccountService } from 'app/core';
import { TypeInsecteService } from './type-insecte.service';

@Component({
    selector: 'jhi-type-insecte',
    templateUrl: './type-insecte.component.html'
})
export class TypeInsecteComponent implements OnInit, OnDestroy {
    typeInsectes: ITypeInsecte[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected typeInsecteService: TypeInsecteService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.typeInsecteService
            .query()
            .pipe(
                filter((res: HttpResponse<ITypeInsecte[]>) => res.ok),
                map((res: HttpResponse<ITypeInsecte[]>) => res.body)
            )
            .subscribe(
                (res: ITypeInsecte[]) => {
                    this.typeInsectes = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTypeInsectes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITypeInsecte) {
        return item.id;
    }

    registerChangeInTypeInsectes() {
        this.eventSubscriber = this.eventManager.subscribe('typeInsecteListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
