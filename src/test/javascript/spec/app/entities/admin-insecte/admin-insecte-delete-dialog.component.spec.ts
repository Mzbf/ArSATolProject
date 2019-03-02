/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ArsatollserviceTestModule } from '../../../test.module';
import { AdminInsecteDeleteDialogComponent } from 'app/entities/admin-insecte/admin-insecte-delete-dialog.component';
import { AdminInsecteService } from 'app/entities/admin-insecte/admin-insecte.service';

describe('Component Tests', () => {
    describe('AdminInsecte Management Delete Component', () => {
        let comp: AdminInsecteDeleteDialogComponent;
        let fixture: ComponentFixture<AdminInsecteDeleteDialogComponent>;
        let service: AdminInsecteService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [AdminInsecteDeleteDialogComponent]
            })
                .overrideTemplate(AdminInsecteDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AdminInsecteDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdminInsecteService);
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
