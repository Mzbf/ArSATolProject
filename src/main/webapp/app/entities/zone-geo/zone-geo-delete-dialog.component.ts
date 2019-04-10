import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IZoneGeo } from 'app/shared/model/zone-geo.model';
import { ZoneGeoService } from './zone-geo.service';

@Component({
    selector: 'jhi-zone-geo-delete-dialog',
    templateUrl: './zone-geo-delete-dialog.component.html'
})
export class ZoneGeoDeleteDialogComponent {
    zoneGeo: IZoneGeo;

    constructor(protected zoneGeoService: ZoneGeoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.zoneGeoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'zoneGeoListModification',
                content: 'Deleted an zoneGeo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-zone-geo-delete-popup',
    template: ''
})
export class ZoneGeoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ zoneGeo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ZoneGeoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.zoneGeo = zoneGeo;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/zone-geo', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/zone-geo', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
