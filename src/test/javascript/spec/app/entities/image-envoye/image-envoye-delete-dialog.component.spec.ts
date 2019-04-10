/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageEnvoyeDeleteDialogComponent } from 'app/entities/image-envoye/image-envoye-delete-dialog.component';
import { ImageEnvoyeService } from 'app/entities/image-envoye/image-envoye.service';

describe('Component Tests', () => {
    describe('ImageEnvoye Management Delete Component', () => {
        let comp: ImageEnvoyeDeleteDialogComponent;
        let fixture: ComponentFixture<ImageEnvoyeDeleteDialogComponent>;
        let service: ImageEnvoyeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageEnvoyeDeleteDialogComponent]
            })
                .overrideTemplate(ImageEnvoyeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImageEnvoyeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageEnvoyeService);
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
