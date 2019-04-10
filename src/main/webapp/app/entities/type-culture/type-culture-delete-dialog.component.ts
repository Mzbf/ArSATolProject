import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeCulture } from 'app/shared/model/type-culture.model';
import { TypeCultureService } from './type-culture.service';

@Component({
    selector: 'jhi-type-culture-delete-dialog',
    templateUrl: './type-culture-delete-dialog.component.html'
})
export class TypeCultureDeleteDialogComponent {
    typeCulture: ITypeCulture;

    constructor(
        protected typeCultureService: TypeCultureService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.typeCultureService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'typeCultureListModification',
                content: 'Deleted an typeCulture'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-type-culture-delete-popup',
    template: ''
})
export class TypeCultureDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeCulture }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TypeCultureDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.typeCulture = typeCulture;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/type-culture', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/type-culture', { outlets: { popup: null } }]);
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
