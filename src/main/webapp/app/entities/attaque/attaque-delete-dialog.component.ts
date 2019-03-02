import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAttaque } from 'app/shared/model/attaque.model';
import { AttaqueService } from './attaque.service';

@Component({
    selector: 'jhi-attaque-delete-dialog',
    templateUrl: './attaque-delete-dialog.component.html'
})
export class AttaqueDeleteDialogComponent {
    attaque: IAttaque;

    constructor(protected attaqueService: AttaqueService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.attaqueService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'attaqueListModification',
                content: 'Deleted an attaque'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-attaque-delete-popup',
    template: ''
})
export class AttaqueDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ attaque }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AttaqueDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.attaque = attaque;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/attaque', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/attaque', { outlets: { popup: null } }]);
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
