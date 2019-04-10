/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ArsatollserviceTestModule } from '../../../test.module';
import { InsecteRavageurDeleteDialogComponent } from 'app/entities/insecte-ravageur/insecte-ravageur-delete-dialog.component';
import { InsecteRavageurService } from 'app/entities/insecte-ravageur/insecte-ravageur.service';

describe('Component Tests', () => {
    describe('InsecteRavageur Management Delete Component', () => {
        let comp: InsecteRavageurDeleteDialogComponent;
        let fixture: ComponentFixture<InsecteRavageurDeleteDialogComponent>;
        let service: InsecteRavageurService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [InsecteRavageurDeleteDialogComponent]
            })
                .overrideTemplate(InsecteRavageurDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InsecteRavageurDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InsecteRavageurService);
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
