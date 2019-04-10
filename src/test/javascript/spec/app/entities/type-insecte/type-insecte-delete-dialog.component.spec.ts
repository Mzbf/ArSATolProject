/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ArsatollserviceTestModule } from '../../../test.module';
import { TypeInsecteDeleteDialogComponent } from 'app/entities/type-insecte/type-insecte-delete-dialog.component';
import { TypeInsecteService } from 'app/entities/type-insecte/type-insecte.service';

describe('Component Tests', () => {
    describe('TypeInsecte Management Delete Component', () => {
        let comp: TypeInsecteDeleteDialogComponent;
        let fixture: ComponentFixture<TypeInsecteDeleteDialogComponent>;
        let service: TypeInsecteService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [TypeInsecteDeleteDialogComponent]
            })
                .overrideTemplate(TypeInsecteDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeInsecteDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeInsecteService);
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
