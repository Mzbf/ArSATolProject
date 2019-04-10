/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { TypeCultureUpdateComponent } from 'app/entities/type-culture/type-culture-update.component';
import { TypeCultureService } from 'app/entities/type-culture/type-culture.service';
import { TypeCulture } from 'app/shared/model/type-culture.model';

describe('Component Tests', () => {
    describe('TypeCulture Management Update Component', () => {
        let comp: TypeCultureUpdateComponent;
        let fixture: ComponentFixture<TypeCultureUpdateComponent>;
        let service: TypeCultureService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [TypeCultureUpdateComponent]
            })
                .overrideTemplate(TypeCultureUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeCultureUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeCultureService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TypeCulture(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.typeCulture = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TypeCulture();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.typeCulture = entity;
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
