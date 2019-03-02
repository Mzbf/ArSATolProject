/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { AttaqueUpdateComponent } from 'app/entities/attaque/attaque-update.component';
import { AttaqueService } from 'app/entities/attaque/attaque.service';
import { Attaque } from 'app/shared/model/attaque.model';

describe('Component Tests', () => {
    describe('Attaque Management Update Component', () => {
        let comp: AttaqueUpdateComponent;
        let fixture: ComponentFixture<AttaqueUpdateComponent>;
        let service: AttaqueService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [AttaqueUpdateComponent]
            })
                .overrideTemplate(AttaqueUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AttaqueUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttaqueService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Attaque(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.attaque = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Attaque();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.attaque = entity;
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
