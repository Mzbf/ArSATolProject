import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IImageCulture } from 'app/shared/model/image-culture.model';
import { AccountService } from 'app/core';
import { ImageCultureService } from './image-culture.service';

@Component({
    selector: 'jhi-image-culture',
    templateUrl: './image-culture.component.html'
})
export class ImageCultureComponent implements OnInit, OnDestroy {
    imageCultures: IImageCulture[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected imageCultureService: ImageCultureService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.imageCultureService
            .query()
            .pipe(
                filter((res: HttpResponse<IImageCulture[]>) => res.ok),
                map((res: HttpResponse<IImageCulture[]>) => res.body)
            )
            .subscribe(
                (res: IImageCulture[]) => {
                    this.imageCultures = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInImageCultures();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IImageCulture) {
        return item.id;
    }

    registerChangeInImageCultures() {
        this.eventSubscriber = this.eventManager.subscribe('imageCultureListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
