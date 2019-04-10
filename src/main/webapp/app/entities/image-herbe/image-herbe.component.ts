import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IImageHerbe } from 'app/shared/model/image-herbe.model';
import { AccountService } from 'app/core';
import { ImageHerbeService } from './image-herbe.service';

@Component({
    selector: 'jhi-image-herbe',
    templateUrl: './image-herbe.component.html'
})
export class ImageHerbeComponent implements OnInit, OnDestroy {
    imageHerbes: IImageHerbe[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected imageHerbeService: ImageHerbeService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.imageHerbeService
            .query()
            .pipe(
                filter((res: HttpResponse<IImageHerbe[]>) => res.ok),
                map((res: HttpResponse<IImageHerbe[]>) => res.body)
            )
            .subscribe(
                (res: IImageHerbe[]) => {
                    this.imageHerbes = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInImageHerbes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IImageHerbe) {
        return item.id;
    }

    registerChangeInImageHerbes() {
        this.eventSubscriber = this.eventManager.subscribe('imageHerbeListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
