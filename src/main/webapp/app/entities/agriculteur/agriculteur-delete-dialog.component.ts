import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAgriculteur } from 'app/shared/model/agriculteur.model';
import { AgriculteurService } from './agriculteur.service';

@Component({
    selector: 'jhi-agriculteur-delete-dialog',
    templateUrl: './agriculteur-delete-dialog.component.html'
})
export class AgriculteurDeleteDialogComponent {
    agriculteur: IAgriculteur;

    constructor(
        protected agriculteurService: AgriculteurService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.agriculteurService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'agriculteurListModification',
                content: 'Deleted an agriculteur'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-agriculteur-delete-popup',
    template: ''
})
export class AgriculteurDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ agriculteur }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AgriculteurDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.agriculteur = agriculteur;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/agriculteur', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/agriculteur', { outlets: { popup: null } }]);
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
