/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { HerbeUpdateComponent } from 'app/entities/herbe/herbe-update.component';
import { HerbeService } from 'app/entities/herbe/herbe.service';
import { Herbe } from 'app/shared/model/herbe.model';

describe('Component Tests', () => {
    describe('Herbe Management Update Component', () => {
        let comp: HerbeUpdateComponent;
        let fixture: ComponentFixture<HerbeUpdateComponent>;
        let service: HerbeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [HerbeUpdateComponent]
            })
                .overrideTemplate(HerbeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(HerbeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HerbeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Herbe(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.herbe = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Herbe();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.herbe = entity;
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
