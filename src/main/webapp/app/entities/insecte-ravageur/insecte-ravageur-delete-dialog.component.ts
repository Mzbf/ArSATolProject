import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInsecteRavageur } from 'app/shared/model/insecte-ravageur.model';
import { InsecteRavageurService } from './insecte-ravageur.service';

@Component({
    selector: 'jhi-insecte-ravageur-delete-dialog',
    templateUrl: './insecte-ravageur-delete-dialog.component.html'
})
export class InsecteRavageurDeleteDialogComponent {
    insecteRavageur: IInsecteRavageur;

    constructor(
        protected insecteRavageurService: InsecteRavageurService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.insecteRavageurService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'insecteRavageurListModification',
                content: 'Deleted an insecteRavageur'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-insecte-ravageur-delete-popup',
    template: ''
})
export class InsecteRavageurDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ insecteRavageur }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(InsecteRavageurDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.insecteRavageur = insecteRavageur;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/insecte-ravageur', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/insecte-ravageur', { outlets: { popup: null } }]);
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
