/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ChercheurInsecteUpdateComponent } from 'app/entities/chercheur-insecte/chercheur-insecte-update.component';
import { ChercheurInsecteService } from 'app/entities/chercheur-insecte/chercheur-insecte.service';
import { ChercheurInsecte } from 'app/shared/model/chercheur-insecte.model';

describe('Component Tests', () => {
    describe('ChercheurInsecte Management Update Component', () => {
        let comp: ChercheurInsecteUpdateComponent;
        let fixture: ComponentFixture<ChercheurInsecteUpdateComponent>;
        let service: ChercheurInsecteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ChercheurInsecteUpdateComponent]
            })
                .overrideTemplate(ChercheurInsecteUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ChercheurInsecteUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChercheurInsecteService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ChercheurInsecte(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.chercheurInsecte = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ChercheurInsecte();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.chercheurInsecte = entity;
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
