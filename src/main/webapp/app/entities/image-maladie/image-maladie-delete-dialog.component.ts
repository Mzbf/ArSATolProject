import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IImageMaladie } from 'app/shared/model/image-maladie.model';
import { ImageMaladieService } from './image-maladie.service';

@Component({
    selector: 'jhi-image-maladie-delete-dialog',
    templateUrl: './image-maladie-delete-dialog.component.html'
})
export class ImageMaladieDeleteDialogComponent {
    imageMaladie: IImageMaladie;

    constructor(
        protected imageMaladieService: ImageMaladieService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.imageMaladieService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'imageMaladieListModification',
                content: 'Deleted an imageMaladie'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-image-maladie-delete-popup',
    template: ''
})
export class ImageMaladieDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ imageMaladie }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ImageMaladieDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.imageMaladie = imageMaladie;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/image-maladie', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/image-maladie', { outlets: { popup: null } }]);
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
