/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { TypeInsecteUpdateComponent } from 'app/entities/type-insecte/type-insecte-update.component';
import { TypeInsecteService } from 'app/entities/type-insecte/type-insecte.service';
import { TypeInsecte } from 'app/shared/model/type-insecte.model';

describe('Component Tests', () => {
    describe('TypeInsecte Management Update Component', () => {
        let comp: TypeInsecteUpdateComponent;
        let fixture: ComponentFixture<TypeInsecteUpdateComponent>;
        let service: TypeInsecteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [TypeInsecteUpdateComponent]
            })
                .overrideTemplate(TypeInsecteUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeInsecteUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeInsecteService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TypeInsecte(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.typeInsecte = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TypeInsecte();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.typeInsecte = entity;
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
