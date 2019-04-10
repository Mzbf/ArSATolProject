/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { InsecteUpdateComponent } from 'app/entities/insecte/insecte-update.component';
import { InsecteService } from 'app/entities/insecte/insecte.service';
import { Insecte } from 'app/shared/model/insecte.model';

describe('Component Tests', () => {
    describe('Insecte Management Update Component', () => {
        let comp: InsecteUpdateComponent;
        let fixture: ComponentFixture<InsecteUpdateComponent>;
        let service: InsecteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [InsecteUpdateComponent]
            })
                .overrideTemplate(InsecteUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InsecteUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InsecteService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Insecte(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.insecte = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Insecte();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.insecte = entity;
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
