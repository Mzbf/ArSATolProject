/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ChercheurInsecteDeleteDialogComponent } from 'app/entities/chercheur-insecte/chercheur-insecte-delete-dialog.component';
import { ChercheurInsecteService } from 'app/entities/chercheur-insecte/chercheur-insecte.service';

describe('Component Tests', () => {
    describe('ChercheurInsecte Management Delete Component', () => {
        let comp: ChercheurInsecteDeleteDialogComponent;
        let fixture: ComponentFixture<ChercheurInsecteDeleteDialogComponent>;
        let service: ChercheurInsecteService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ChercheurInsecteDeleteDialogComponent]
            })
                .overrideTemplate(ChercheurInsecteDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ChercheurInsecteDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChercheurInsecteService);
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
