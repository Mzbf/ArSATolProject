/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageHerbeDeleteDialogComponent } from 'app/entities/image-herbe/image-herbe-delete-dialog.component';
import { ImageHerbeService } from 'app/entities/image-herbe/image-herbe.service';

describe('Component Tests', () => {
    describe('ImageHerbe Management Delete Component', () => {
        let comp: ImageHerbeDeleteDialogComponent;
        let fixture: ComponentFixture<ImageHerbeDeleteDialogComponent>;
        let service: ImageHerbeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageHerbeDeleteDialogComponent]
            })
                .overrideTemplate(ImageHerbeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImageHerbeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageHerbeService);
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
