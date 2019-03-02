/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ArsatollserviceTestModule } from '../../../test.module';
import { MethodeLutteDeleteDialogComponent } from 'app/entities/methode-lutte/methode-lutte-delete-dialog.component';
import { MethodeLutteService } from 'app/entities/methode-lutte/methode-lutte.service';

describe('Component Tests', () => {
    describe('MethodeLutte Management Delete Component', () => {
        let comp: MethodeLutteDeleteDialogComponent;
        let fixture: ComponentFixture<MethodeLutteDeleteDialogComponent>;
        let service: MethodeLutteService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [MethodeLutteDeleteDialogComponent]
            })
                .overrideTemplate(MethodeLutteDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MethodeLutteDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MethodeLutteService);
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
