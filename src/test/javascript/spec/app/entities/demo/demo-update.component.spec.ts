/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { DemoUpdateComponent } from 'app/entities/demo/demo-update.component';
import { DemoService } from 'app/entities/demo/demo.service';
import { Demo } from 'app/shared/model/demo.model';

describe('Component Tests', () => {
    describe('Demo Management Update Component', () => {
        let comp: DemoUpdateComponent;
        let fixture: ComponentFixture<DemoUpdateComponent>;
        let service: DemoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [DemoUpdateComponent]
            })
                .overrideTemplate(DemoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DemoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DemoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Demo(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.demo = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Demo();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.demo = entity;
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
