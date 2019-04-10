import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDemo } from 'app/shared/model/demo.model';
import { DemoService } from './demo.service';

@Component({
    selector: 'jhi-demo-delete-dialog',
    templateUrl: './demo-delete-dialog.component.html'
})
export class DemoDeleteDialogComponent {
    demo: IDemo;

    constructor(protected demoService: DemoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.demoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'demoListModification',
                content: 'Deleted an demo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-demo-delete-popup',
    template: ''
})
export class DemoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ demo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DemoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.demo = demo;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/demo', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/demo', { outlets: { popup: null } }]);
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
