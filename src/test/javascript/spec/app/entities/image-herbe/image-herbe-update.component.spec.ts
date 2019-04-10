/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageHerbeUpdateComponent } from 'app/entities/image-herbe/image-herbe-update.component';
import { ImageHerbeService } from 'app/entities/image-herbe/image-herbe.service';
import { ImageHerbe } from 'app/shared/model/image-herbe.model';

describe('Component Tests', () => {
    describe('ImageHerbe Management Update Component', () => {
        let comp: ImageHerbeUpdateComponent;
        let fixture: ComponentFixture<ImageHerbeUpdateComponent>;
        let service: ImageHerbeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageHerbeUpdateComponent]
            })
                .overrideTemplate(ImageHerbeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImageHerbeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageHerbeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ImageHerbe(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.imageHerbe = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ImageHerbe();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.imageHerbe = entity;
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
