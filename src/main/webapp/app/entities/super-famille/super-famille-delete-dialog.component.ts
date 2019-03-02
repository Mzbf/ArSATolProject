import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISuperFamille } from 'app/shared/model/super-famille.model';
import { SuperFamilleService } from './super-famille.service';

@Component({
    selector: 'jhi-super-famille-delete-dialog',
    templateUrl: './super-famille-delete-dialog.component.html'
})
export class SuperFamilleDeleteDialogComponent {
    superFamille: ISuperFamille;

    constructor(
        protected superFamilleService: SuperFamilleService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.superFamilleService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'superFamilleListModification',
                content: 'Deleted an superFamille'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-super-famille-delete-popup',
    template: ''
})
export class SuperFamilleDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ superFamille }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SuperFamilleDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.superFamille = superFamille;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/super-famille', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/super-famille', { outlets: { popup: null } }]);
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
