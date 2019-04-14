/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageCultureUpdateComponent } from 'app/entities/image-culture/image-culture-update.component';
import { ImageCultureService } from 'app/entities/image-culture/image-culture.service';
import { ImageCulture } from 'app/shared/model/image-culture.model';
import { FormGroup } from '@angular/forms';

describe('Component Tests', () => {
    describe('ImageCulture Management Update Component', () => {
        let comp: ImageCultureUpdateComponent;
        let fixture: ComponentFixture<ImageCultureUpdateComponent>;
        let service: ImageCultureService;
        let edit: FormGroup;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageCultureUpdateComponent]
            })
                .overrideTemplate(ImageCultureUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImageCultureUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageCultureService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ImageCulture(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.imageCulture = entity;
                // WHEN
                comp.save(edit);
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ImageCulture();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.imageCulture = entity;
                // WHEN
                comp.save(edit);
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
