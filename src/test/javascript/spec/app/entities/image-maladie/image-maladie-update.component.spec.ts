/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageMaladieUpdateComponent } from 'app/entities/image-maladie/image-maladie-update.component';
import { ImageMaladieService } from 'app/entities/image-maladie/image-maladie.service';
import { ImageMaladie } from 'app/shared/model/image-maladie.model';

describe('Component Tests', () => {
    describe('ImageMaladie Management Update Component', () => {
        let comp: ImageMaladieUpdateComponent;
        let fixture: ComponentFixture<ImageMaladieUpdateComponent>;
        let service: ImageMaladieService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageMaladieUpdateComponent]
            })
                .overrideTemplate(ImageMaladieUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImageMaladieUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageMaladieService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ImageMaladie(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.imageMaladie = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ImageMaladie();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.imageMaladie = entity;
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
