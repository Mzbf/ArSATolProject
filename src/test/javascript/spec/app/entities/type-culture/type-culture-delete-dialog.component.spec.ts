/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ArsatollserviceTestModule } from '../../../test.module';
import { TypeCultureDeleteDialogComponent } from 'app/entities/type-culture/type-culture-delete-dialog.component';
import { TypeCultureService } from 'app/entities/type-culture/type-culture.service';

describe('Component Tests', () => {
    describe('TypeCulture Management Delete Component', () => {
        let comp: TypeCultureDeleteDialogComponent;
        let fixture: ComponentFixture<TypeCultureDeleteDialogComponent>;
        let service: TypeCultureService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [TypeCultureDeleteDialogComponent]
            })
                .overrideTemplate(TypeCultureDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeCultureDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeCultureService);
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
