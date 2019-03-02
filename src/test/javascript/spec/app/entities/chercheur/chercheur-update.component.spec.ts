/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ChercheurUpdateComponent } from 'app/entities/chercheur/chercheur-update.component';
import { ChercheurService } from 'app/entities/chercheur/chercheur.service';
import { Chercheur } from 'app/shared/model/chercheur.model';

describe('Component Tests', () => {
    describe('Chercheur Management Update Component', () => {
        let comp: ChercheurUpdateComponent;
        let fixture: ComponentFixture<ChercheurUpdateComponent>;
        let service: ChercheurService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ChercheurUpdateComponent]
            })
                .overrideTemplate(ChercheurUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ChercheurUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChercheurService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Chercheur(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.chercheur = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Chercheur();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.chercheur = entity;
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
