/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { MethodeLutteUpdateComponent } from 'app/entities/methode-lutte/methode-lutte-update.component';
import { MethodeLutteService } from 'app/entities/methode-lutte/methode-lutte.service';
import { MethodeLutte } from 'app/shared/model/methode-lutte.model';

describe('Component Tests', () => {
    describe('MethodeLutte Management Update Component', () => {
        let comp: MethodeLutteUpdateComponent;
        let fixture: ComponentFixture<MethodeLutteUpdateComponent>;
        let service: MethodeLutteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [MethodeLutteUpdateComponent]
            })
                .overrideTemplate(MethodeLutteUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MethodeLutteUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MethodeLutteService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new MethodeLutte(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.methodeLutte = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new MethodeLutte();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.methodeLutte = entity;
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
