/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { AdministrateurUpdateComponent } from 'app/entities/administrateur/administrateur-update.component';
import { AdministrateurService } from 'app/entities/administrateur/administrateur.service';
import { Administrateur } from 'app/shared/model/administrateur.model';

describe('Component Tests', () => {
    describe('Administrateur Management Update Component', () => {
        let comp: AdministrateurUpdateComponent;
        let fixture: ComponentFixture<AdministrateurUpdateComponent>;
        let service: AdministrateurService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [AdministrateurUpdateComponent]
            })
                .overrideTemplate(AdministrateurUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AdministrateurUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdministrateurService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Administrateur(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.administrateur = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Administrateur();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.administrateur = entity;
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
