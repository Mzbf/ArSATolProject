import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMethodeLutte } from 'app/shared/model/methode-lutte.model';
import { MethodeLutteService } from './methode-lutte.service';

@Component({
    selector: 'jhi-methode-lutte-delete-dialog',
    templateUrl: './methode-lutte-delete-dialog.component.html'
})
export class MethodeLutteDeleteDialogComponent {
    methodeLutte: IMethodeLutte;

    constructor(
        protected methodeLutteService: MethodeLutteService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.methodeLutteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'methodeLutteListModification',
                content: 'Deleted an methodeLutte'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-methode-lutte-delete-popup',
    template: ''
})
export class MethodeLutteDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ methodeLutte }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MethodeLutteDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.methodeLutte = methodeLutte;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/methode-lutte', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/methode-lutte', { outlets: { popup: null } }]);
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
