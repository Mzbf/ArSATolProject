/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { InsecteRavageurUpdateComponent } from 'app/entities/insecte-ravageur/insecte-ravageur-update.component';
import { InsecteRavageurService } from 'app/entities/insecte-ravageur/insecte-ravageur.service';
import { InsecteRavageur } from 'app/shared/model/insecte-ravageur.model';

describe('Component Tests', () => {
    describe('InsecteRavageur Management Update Component', () => {
        let comp: InsecteRavageurUpdateComponent;
        let fixture: ComponentFixture<InsecteRavageurUpdateComponent>;
        let service: InsecteRavageurService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [InsecteRavageurUpdateComponent]
            })
                .overrideTemplate(InsecteRavageurUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InsecteRavageurUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InsecteRavageurService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new InsecteRavageur(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.insecteRavageur = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new InsecteRavageur();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.insecteRavageur = entity;
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
