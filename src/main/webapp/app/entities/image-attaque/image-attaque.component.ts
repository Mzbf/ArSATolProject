import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IImageAttaque } from 'app/shared/model/image-attaque.model';
import { AccountService } from 'app/core';
import { ImageAttaqueService } from './image-attaque.service';

@Component({
    selector: 'jhi-image-attaque',
    templateUrl: './image-attaque.component.html'
})
export class ImageAttaqueComponent implements OnInit, OnDestroy {
    imageAttaques: IImageAttaque[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected imageAttaqueService: ImageAttaqueService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.imageAttaqueService
            .query()
            .pipe(
                filter((res: HttpResponse<IImageAttaque[]>) => res.ok),
                map((res: HttpResponse<IImageAttaque[]>) => res.body)
            )
            .subscribe(
                (res: IImageAttaque[]) => {
                    this.imageAttaques = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInImageAttaques();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IImageAttaque) {
        return item.id;
    }

    registerChangeInImageAttaques() {
        this.eventSubscriber = this.eventManager.subscribe('imageAttaqueListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
