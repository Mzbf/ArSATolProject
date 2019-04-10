import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IImageAttaque } from 'app/shared/model/image-attaque.model';
import { ImageAttaqueService } from './image-attaque.service';

@Component({
    selector: 'jhi-image-attaque-delete-dialog',
    templateUrl: './image-attaque-delete-dialog.component.html'
})
export class ImageAttaqueDeleteDialogComponent {
    imageAttaque: IImageAttaque;

    constructor(
        protected imageAttaqueService: ImageAttaqueService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.imageAttaqueService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'imageAttaqueListModification',
                content: 'Deleted an imageAttaque'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-image-attaque-delete-popup',
    template: ''
})
export class ImageAttaqueDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ imageAttaque }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ImageAttaqueDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.imageAttaque = imageAttaque;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/image-attaque', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/image-attaque', { outlets: { popup: null } }]);
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
