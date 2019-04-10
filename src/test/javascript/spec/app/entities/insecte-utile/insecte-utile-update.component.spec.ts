/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { InsecteUtileUpdateComponent } from 'app/entities/insecte-utile/insecte-utile-update.component';
import { InsecteUtileService } from 'app/entities/insecte-utile/insecte-utile.service';
import { InsecteUtile } from 'app/shared/model/insecte-utile.model';

describe('Component Tests', () => {
    describe('InsecteUtile Management Update Component', () => {
        let comp: InsecteUtileUpdateComponent;
        let fixture: ComponentFixture<InsecteUtileUpdateComponent>;
        let service: InsecteUtileService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [InsecteUtileUpdateComponent]
            })
                .overrideTemplate(InsecteUtileUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InsecteUtileUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InsecteUtileService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new InsecteUtile(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.insecteUtile = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new InsecteUtile();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.insecteUtile = entity;
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
