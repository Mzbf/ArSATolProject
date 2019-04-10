import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeDegat } from 'app/shared/model/type-degat.model';
import { TypeDegatService } from './type-degat.service';

@Component({
    selector: 'jhi-type-degat-delete-dialog',
    templateUrl: './type-degat-delete-dialog.component.html'
})
export class TypeDegatDeleteDialogComponent {
    typeDegat: ITypeDegat;

    constructor(
        protected typeDegatService: TypeDegatService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.typeDegatService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'typeDegatListModification',
                content: 'Deleted an typeDegat'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-type-degat-delete-popup',
    template: ''
})
export class TypeDegatDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeDegat }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TypeDegatDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.typeDegat = typeDegat;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/type-degat', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/type-degat', { outlets: { popup: null } }]);
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
