import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdminInsecte } from 'app/shared/model/admin-insecte.model';
import { AdminInsecteService } from './admin-insecte.service';

@Component({
    selector: 'jhi-admin-insecte-delete-dialog',
    templateUrl: './admin-insecte-delete-dialog.component.html'
})
export class AdminInsecteDeleteDialogComponent {
    adminInsecte: IAdminInsecte;

    constructor(
        protected adminInsecteService: AdminInsecteService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.adminInsecteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'adminInsecteListModification',
                content: 'Deleted an adminInsecte'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-admin-insecte-delete-popup',
    template: ''
})
export class AdminInsecteDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ adminInsecte }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AdminInsecteDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.adminInsecte = adminInsecte;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/admin-insecte', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/admin-insecte', { outlets: { popup: null } }]);
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
