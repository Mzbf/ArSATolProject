import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHerbe } from 'app/shared/model/herbe.model';
import { HerbeService } from './herbe.service';

@Component({
    selector: 'jhi-herbe-delete-dialog',
    templateUrl: './herbe-delete-dialog.component.html'
})
export class HerbeDeleteDialogComponent {
    herbe: IHerbe;

    constructor(protected herbeService: HerbeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.herbeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'herbeListModification',
                content: 'Deleted an herbe'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-herbe-delete-popup',
    template: ''
})
export class HerbeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ herbe }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(HerbeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.herbe = herbe;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/herbe', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/herbe', { outlets: { popup: null } }]);
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
