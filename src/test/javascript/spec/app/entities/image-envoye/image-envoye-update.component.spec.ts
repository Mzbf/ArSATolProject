/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageEnvoyeUpdateComponent } from 'app/entities/image-envoye/image-envoye-update.component';
import { ImageEnvoyeService } from 'app/entities/image-envoye/image-envoye.service';
import { ImageEnvoye } from 'app/shared/model/image-envoye.model';

describe('Component Tests', () => {
    describe('ImageEnvoye Management Update Component', () => {
        let comp: ImageEnvoyeUpdateComponent;
        let fixture: ComponentFixture<ImageEnvoyeUpdateComponent>;
        let service: ImageEnvoyeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageEnvoyeUpdateComponent]
            })
                .overrideTemplate(ImageEnvoyeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImageEnvoyeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageEnvoyeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ImageEnvoye(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.imageEnvoye = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ImageEnvoye();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.imageEnvoye = entity;
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
