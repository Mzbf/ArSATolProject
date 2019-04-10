/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ArsatollserviceTestModule } from '../../../test.module';
import { TypeDegatDeleteDialogComponent } from 'app/entities/type-degat/type-degat-delete-dialog.component';
import { TypeDegatService } from 'app/entities/type-degat/type-degat.service';

describe('Component Tests', () => {
    describe('TypeDegat Management Delete Component', () => {
        let comp: TypeDegatDeleteDialogComponent;
        let fixture: ComponentFixture<TypeDegatDeleteDialogComponent>;
        let service: TypeDegatService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [TypeDegatDeleteDialogComponent]
            })
                .overrideTemplate(TypeDegatDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeDegatDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeDegatService);
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
