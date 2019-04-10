import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IImageInsecte } from 'app/shared/model/image-insecte.model';
import { ImageInsecteService } from './image-insecte.service';

@Component({
    selector: 'jhi-image-insecte-delete-dialog',
    templateUrl: './image-insecte-delete-dialog.component.html'
})
export class ImageInsecteDeleteDialogComponent {
    imageInsecte: IImageInsecte;

    constructor(
        protected imageInsecteService: ImageInsecteService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.imageInsecteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'imageInsecteListModification',
                content: 'Deleted an imageInsecte'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-image-insecte-delete-popup',
    template: ''
})
export class ImageInsecteDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ imageInsecte }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ImageInsecteDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.imageInsecte = imageInsecte;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/image-insecte', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/image-insecte', { outlets: { popup: null } }]);
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
