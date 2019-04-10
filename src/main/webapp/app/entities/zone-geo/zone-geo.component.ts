import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IZoneGeo } from 'app/shared/model/zone-geo.model';
import { AccountService } from 'app/core';
import { ZoneGeoService } from './zone-geo.service';

@Component({
    selector: 'jhi-zone-geo',
    templateUrl: './zone-geo.component.html'
})
export class ZoneGeoComponent implements OnInit, OnDestroy {
    zoneGeos: IZoneGeo[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected zoneGeoService: ZoneGeoService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.zoneGeoService
            .query()
            .pipe(
                filter((res: HttpResponse<IZoneGeo[]>) => res.ok),
                map((res: HttpResponse<IZoneGeo[]>) => res.body)
            )
            .subscribe(
                (res: IZoneGeo[]) => {
                    this.zoneGeos = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInZoneGeos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IZoneGeo) {
        return item.id;
    }

    registerChangeInZoneGeos() {
        this.eventSubscriber = this.eventManager.subscribe('zoneGeoListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
