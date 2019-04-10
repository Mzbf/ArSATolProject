import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInsecteUtile } from 'app/shared/model/insecte-utile.model';
import { InsecteUtileService } from './insecte-utile.service';

@Component({
    selector: 'jhi-insecte-utile-delete-dialog',
    templateUrl: './insecte-utile-delete-dialog.component.html'
})
export class InsecteUtileDeleteDialogComponent {
    insecteUtile: IInsecteUtile;

    constructor(
        protected insecteUtileService: InsecteUtileService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.insecteUtileService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'insecteUtileListModification',
                content: 'Deleted an insecteUtile'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-insecte-utile-delete-popup',
    template: ''
})
export class InsecteUtileDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ insecteUtile }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(InsecteUtileDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.insecteUtile = insecteUtile;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/insecte-utile', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/insecte-utile', { outlets: { popup: null } }]);
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
