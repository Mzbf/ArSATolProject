/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageAttaqueDeleteDialogComponent } from 'app/entities/image-attaque/image-attaque-delete-dialog.component';
import { ImageAttaqueService } from 'app/entities/image-attaque/image-attaque.service';

describe('Component Tests', () => {
    describe('ImageAttaque Management Delete Component', () => {
        let comp: ImageAttaqueDeleteDialogComponent;
        let fixture: ComponentFixture<ImageAttaqueDeleteDialogComponent>;
        let service: ImageAttaqueService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageAttaqueDeleteDialogComponent]
            })
                .overrideTemplate(ImageAttaqueDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImageAttaqueDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageAttaqueService);
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
