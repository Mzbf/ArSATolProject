import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IImageEnvoye } from 'app/shared/model/image-envoye.model';
import { AccountService } from 'app/core';
import { ImageEnvoyeService } from './image-envoye.service';

@Component({
    selector: 'jhi-image-envoye',
    templateUrl: './image-envoye.component.html'
})
export class ImageEnvoyeComponent implements OnInit, OnDestroy {
    imageEnvoyes: IImageEnvoye[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected imageEnvoyeService: ImageEnvoyeService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.imageEnvoyeService
            .query()
            .pipe(
                filter((res: HttpResponse<IImageEnvoye[]>) => res.ok),
                map((res: HttpResponse<IImageEnvoye[]>) => res.body)
            )
            .subscribe(
                (res: IImageEnvoye[]) => {
                    this.imageEnvoyes = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInImageEnvoyes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IImageEnvoye) {
        return item.id;
    }

    registerChangeInImageEnvoyes() {
        this.eventSubscriber = this.eventManager.subscribe('imageEnvoyeListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
