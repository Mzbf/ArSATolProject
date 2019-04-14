/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageAttaqueUpdateComponent } from 'app/entities/image-attaque/image-attaque-update.component';
import { ImageAttaqueService } from 'app/entities/image-attaque/image-attaque.service';
import { ImageAttaque } from 'app/shared/model/image-attaque.model';
import { FormGroup } from '@angular/forms';

describe('Component Tests', () => {
    describe('ImageAttaque Management Update Component', () => {
        let comp: ImageAttaqueUpdateComponent;
        let fixture: ComponentFixture<ImageAttaqueUpdateComponent>;
        let service: ImageAttaqueService;
        let editForm: FormGroup;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageAttaqueUpdateComponent]
            })
                .overrideTemplate(ImageAttaqueUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImageAttaqueUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageAttaqueService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ImageAttaque(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.imageAttaque = entity;
                // WHEN
                comp.save(editForm);
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ImageAttaque();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.imageAttaque = entity;
                // WHEN
                comp.save(editForm);
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
