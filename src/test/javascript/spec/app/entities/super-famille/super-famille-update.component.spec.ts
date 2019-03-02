/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { SuperFamilleUpdateComponent } from 'app/entities/super-famille/super-famille-update.component';
import { SuperFamilleService } from 'app/entities/super-famille/super-famille.service';
import { SuperFamille } from 'app/shared/model/super-famille.model';

describe('Component Tests', () => {
    describe('SuperFamille Management Update Component', () => {
        let comp: SuperFamilleUpdateComponent;
        let fixture: ComponentFixture<SuperFamilleUpdateComponent>;
        let service: SuperFamilleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [SuperFamilleUpdateComponent]
            })
                .overrideTemplate(SuperFamilleUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SuperFamilleUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SuperFamilleService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SuperFamille(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.superFamille = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SuperFamille();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.superFamille = entity;
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
