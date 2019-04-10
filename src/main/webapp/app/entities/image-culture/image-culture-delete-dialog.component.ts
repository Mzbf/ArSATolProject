import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IImageCulture } from 'app/shared/model/image-culture.model';
import { ImageCultureService } from './image-culture.service';

@Component({
    selector: 'jhi-image-culture-delete-dialog',
    templateUrl: './image-culture-delete-dialog.component.html'
})
export class ImageCultureDeleteDialogComponent {
    imageCulture: IImageCulture;

    constructor(
        protected imageCultureService: ImageCultureService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.imageCultureService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'imageCultureListModification',
                content: 'Deleted an imageCulture'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-image-culture-delete-popup',
    template: ''
})
export class ImageCultureDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ imageCulture }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ImageCultureDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.imageCulture = imageCulture;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/image-culture', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/image-culture', { outlets: { popup: null } }]);
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
