import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IChercheurInsecte } from 'app/shared/model/chercheur-insecte.model';
import { ChercheurInsecteService } from './chercheur-insecte.service';

@Component({
    selector: 'jhi-chercheur-insecte-delete-dialog',
    templateUrl: './chercheur-insecte-delete-dialog.component.html'
})
export class ChercheurInsecteDeleteDialogComponent {
    chercheurInsecte: IChercheurInsecte;

    constructor(
        protected chercheurInsecteService: ChercheurInsecteService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.chercheurInsecteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'chercheurInsecteListModification',
                content: 'Deleted an chercheurInsecte'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-chercheur-insecte-delete-popup',
    template: ''
})
export class ChercheurInsecteDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ chercheurInsecte }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ChercheurInsecteDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.chercheurInsecte = chercheurInsecte;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/chercheur-insecte', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/chercheur-insecte', { outlets: { popup: null } }]);
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
