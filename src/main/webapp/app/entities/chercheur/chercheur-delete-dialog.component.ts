import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IChercheur } from 'app/shared/model/chercheur.model';
import { ChercheurService } from './chercheur.service';

@Component({
    selector: 'jhi-chercheur-delete-dialog',
    templateUrl: './chercheur-delete-dialog.component.html'
})
export class ChercheurDeleteDialogComponent {
    chercheur: IChercheur;

    constructor(
        protected chercheurService: ChercheurService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.chercheurService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'chercheurListModification',
                content: 'Deleted an chercheur'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-chercheur-delete-popup',
    template: ''
})
export class ChercheurDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ chercheur }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ChercheurDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.chercheur = chercheur;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/chercheur', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/chercheur', { outlets: { popup: null } }]);
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
