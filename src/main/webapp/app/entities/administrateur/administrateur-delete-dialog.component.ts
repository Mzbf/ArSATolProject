import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdministrateur } from 'app/shared/model/administrateur.model';
import { AdministrateurService } from './administrateur.service';

@Component({
    selector: 'jhi-administrateur-delete-dialog',
    templateUrl: './administrateur-delete-dialog.component.html'
})
export class AdministrateurDeleteDialogComponent {
    administrateur: IAdministrateur;

    constructor(
        protected administrateurService: AdministrateurService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.administrateurService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'administrateurListModification',
                content: 'Deleted an administrateur'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-administrateur-delete-popup',
    template: ''
})
export class AdministrateurDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ administrateur }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AdministrateurDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.administrateur = administrateur;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/administrateur', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/administrateur', { outlets: { popup: null } }]);
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
