/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageMaladieDeleteDialogComponent } from 'app/entities/image-maladie/image-maladie-delete-dialog.component';
import { ImageMaladieService } from 'app/entities/image-maladie/image-maladie.service';

describe('Component Tests', () => {
    describe('ImageMaladie Management Delete Component', () => {
        let comp: ImageMaladieDeleteDialogComponent;
        let fixture: ComponentFixture<ImageMaladieDeleteDialogComponent>;
        let service: ImageMaladieService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageMaladieDeleteDialogComponent]
            })
                .overrideTemplate(ImageMaladieDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImageMaladieDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageMaladieService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
