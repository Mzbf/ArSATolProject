import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IImageEnvoye } from 'app/shared/model/image-envoye.model';
import { ImageEnvoyeService } from './image-envoye.service';

@Component({
    selector: 'jhi-image-envoye-delete-dialog',
    templateUrl: './image-envoye-delete-dialog.component.html'
})
export class ImageEnvoyeDeleteDialogComponent {
    imageEnvoye: IImageEnvoye;

    constructor(
        protected imageEnvoyeService: ImageEnvoyeService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.imageEnvoyeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'imageEnvoyeListModification',
                content: 'Deleted an imageEnvoye'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-image-envoye-delete-popup',
    template: ''
})
export class ImageEnvoyeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ imageEnvoye }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ImageEnvoyeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.imageEnvoye = imageEnvoye;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/image-envoye', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/image-envoye', { outlets: { popup: null } }]);
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
