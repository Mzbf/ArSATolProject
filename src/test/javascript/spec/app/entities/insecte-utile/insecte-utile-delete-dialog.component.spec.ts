/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ArsatollserviceTestModule } from '../../../test.module';
import { InsecteUtileDeleteDialogComponent } from 'app/entities/insecte-utile/insecte-utile-delete-dialog.component';
import { InsecteUtileService } from 'app/entities/insecte-utile/insecte-utile.service';

describe('Component Tests', () => {
    describe('InsecteUtile Management Delete Component', () => {
        let comp: InsecteUtileDeleteDialogComponent;
        let fixture: ComponentFixture<InsecteUtileDeleteDialogComponent>;
        let service: InsecteUtileService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [InsecteUtileDeleteDialogComponent]
            })
                .overrideTemplate(InsecteUtileDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InsecteUtileDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InsecteUtileService);
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
