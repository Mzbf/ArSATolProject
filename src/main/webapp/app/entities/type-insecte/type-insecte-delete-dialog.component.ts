import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeInsecte } from 'app/shared/model/type-insecte.model';
import { TypeInsecteService } from './type-insecte.service';

@Component({
    selector: 'jhi-type-insecte-delete-dialog',
    templateUrl: './type-insecte-delete-dialog.component.html'
})
export class TypeInsecteDeleteDialogComponent {
    typeInsecte: ITypeInsecte;

    constructor(
        protected typeInsecteService: TypeInsecteService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.typeInsecteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'typeInsecteListModification',
                content: 'Deleted an typeInsecte'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-type-insecte-delete-popup',
    template: ''
})
export class TypeInsecteDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeInsecte }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TypeInsecteDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.typeInsecte = typeInsecte;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/type-insecte', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/type-insecte', { outlets: { popup: null } }]);
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
