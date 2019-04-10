/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { TypeDegatUpdateComponent } from 'app/entities/type-degat/type-degat-update.component';
import { TypeDegatService } from 'app/entities/type-degat/type-degat.service';
import { TypeDegat } from 'app/shared/model/type-degat.model';

describe('Component Tests', () => {
    describe('TypeDegat Management Update Component', () => {
        let comp: TypeDegatUpdateComponent;
        let fixture: ComponentFixture<TypeDegatUpdateComponent>;
        let service: TypeDegatService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [TypeDegatUpdateComponent]
            })
                .overrideTemplate(TypeDegatUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeDegatUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeDegatService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TypeDegat(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.typeDegat = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TypeDegat();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.typeDegat = entity;
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
