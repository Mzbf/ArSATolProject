import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IImageInsecte } from 'app/shared/model/image-insecte.model';
import { AccountService } from 'app/core';
import { ImageInsecteService } from './image-insecte.service';

@Component({
    selector: 'jhi-image-insecte',
    templateUrl: './image-insecte.component.html'
})
export class ImageInsecteComponent implements OnInit, OnDestroy {
    imageInsectes: IImageInsecte[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected imageInsecteService: ImageInsecteService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.imageInsecteService
            .query()
            .pipe(
                filter((res: HttpResponse<IImageInsecte[]>) => res.ok),
                map((res: HttpResponse<IImageInsecte[]>) => res.body)
            )
            .subscribe(
                (res: IImageInsecte[]) => {
                    this.imageInsectes = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInImageInsectes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IImageInsecte) {
        return item.id;
    }

    registerChangeInImageInsectes() {
        this.eventSubscriber = this.eventManager.subscribe('imageInsecteListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
