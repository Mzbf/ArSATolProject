import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IImageHerbe } from 'app/shared/model/image-herbe.model';
import { ImageHerbeService } from './image-herbe.service';

@Component({
    selector: 'jhi-image-herbe-delete-dialog',
    templateUrl: './image-herbe-delete-dialog.component.html'
})
export class ImageHerbeDeleteDialogComponent {
    imageHerbe: IImageHerbe;

    constructor(
        protected imageHerbeService: ImageHerbeService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.imageHerbeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'imageHerbeListModification',
                content: 'Deleted an imageHerbe'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-image-herbe-delete-popup',
    template: ''
})
export class ImageHerbeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ imageHerbe }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ImageHerbeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.imageHerbe = imageHerbe;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/image-herbe', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/image-herbe', { outlets: { popup: null } }]);
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
