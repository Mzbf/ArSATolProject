/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ArsatollserviceTestModule } from '../../../test.module';
import { AgriculteurDeleteDialogComponent } from 'app/entities/agriculteur/agriculteur-delete-dialog.component';
import { AgriculteurService } from 'app/entities/agriculteur/agriculteur.service';

describe('Component Tests', () => {
    describe('Agriculteur Management Delete Component', () => {
        let comp: AgriculteurDeleteDialogComponent;
        let fixture: ComponentFixture<AgriculteurDeleteDialogComponent>;
        let service: AgriculteurService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [AgriculteurDeleteDialogComponent]
            })
                .overrideTemplate(AgriculteurDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AgriculteurDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AgriculteurService);
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
