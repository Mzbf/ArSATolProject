/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ArsatollserviceTestModule } from '../../../test.module';
import { SuperFamilleDeleteDialogComponent } from 'app/entities/super-famille/super-famille-delete-dialog.component';
import { SuperFamilleService } from 'app/entities/super-famille/super-famille.service';

describe('Component Tests', () => {
    describe('SuperFamille Management Delete Component', () => {
        let comp: SuperFamilleDeleteDialogComponent;
        let fixture: ComponentFixture<SuperFamilleDeleteDialogComponent>;
        let service: SuperFamilleService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [SuperFamilleDeleteDialogComponent]
            })
                .overrideTemplate(SuperFamilleDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SuperFamilleDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SuperFamilleService);
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
