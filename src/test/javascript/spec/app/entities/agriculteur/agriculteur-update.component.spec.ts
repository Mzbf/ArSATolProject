/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { AgriculteurUpdateComponent } from 'app/entities/agriculteur/agriculteur-update.component';
import { AgriculteurService } from 'app/entities/agriculteur/agriculteur.service';
import { Agriculteur } from 'app/shared/model/agriculteur.model';

describe('Component Tests', () => {
    describe('Agriculteur Management Update Component', () => {
        let comp: AgriculteurUpdateComponent;
        let fixture: ComponentFixture<AgriculteurUpdateComponent>;
        let service: AgriculteurService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [AgriculteurUpdateComponent]
            })
                .overrideTemplate(AgriculteurUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AgriculteurUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AgriculteurService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Agriculteur(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.agriculteur = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Agriculteur();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.agriculteur = entity;
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
