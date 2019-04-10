import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IImageMaladie } from 'app/shared/model/image-maladie.model';
import { AccountService } from 'app/core';
import { ImageMaladieService } from './image-maladie.service';

@Component({
    selector: 'jhi-image-maladie',
    templateUrl: './image-maladie.component.html'
})
export class ImageMaladieComponent implements OnInit, OnDestroy {
    imageMaladies: IImageMaladie[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected imageMaladieService: ImageMaladieService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.imageMaladieService
            .query()
            .pipe(
                filter((res: HttpResponse<IImageMaladie[]>) => res.ok),
                map((res: HttpResponse<IImageMaladie[]>) => res.body)
            )
            .subscribe(
                (res: IImageMaladie[]) => {
                    this.imageMaladies = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInImageMaladies();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IImageMaladie) {
        return item.id;
    }

    registerChangeInImageMaladies() {
        this.eventSubscriber = this.eventManager.subscribe('imageMaladieListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
