/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { AdminInsecteUpdateComponent } from 'app/entities/admin-insecte/admin-insecte-update.component';
import { AdminInsecteService } from 'app/entities/admin-insecte/admin-insecte.service';
import { AdminInsecte } from 'app/shared/model/admin-insecte.model';

describe('Component Tests', () => {
    describe('AdminInsecte Management Update Component', () => {
        let comp: AdminInsecteUpdateComponent;
        let fixture: ComponentFixture<AdminInsecteUpdateComponent>;
        let service: AdminInsecteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [AdminInsecteUpdateComponent]
            })
                .overrideTemplate(AdminInsecteUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AdminInsecteUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdminInsecteService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AdminInsecte(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.adminInsecte = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AdminInsecte();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.adminInsecte = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
