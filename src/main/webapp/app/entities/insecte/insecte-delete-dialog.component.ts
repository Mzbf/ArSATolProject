import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInsecte } from 'app/shared/model/insecte.model';
import { InsecteService } from './insecte.service';

@Component({
    selector: 'jhi-insecte-delete-dialog',
    templateUrl: './insecte-delete-dialog.component.html'
})
export class InsecteDeleteDialogComponent {
    insecte: IInsecte;

    constructor(protected insecteService: InsecteService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.insecteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'insecteListModification',
                content: 'Deleted an insecte'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-insecte-delete-popup',
    template: ''
})
export class InsecteDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ insecte }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(InsecteDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.insecte = insecte;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/insecte', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/insecte', { outlets: { popup: null } }]);
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
