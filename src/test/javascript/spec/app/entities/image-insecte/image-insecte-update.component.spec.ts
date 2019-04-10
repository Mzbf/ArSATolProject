/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageInsecteUpdateComponent } from 'app/entities/image-insecte/image-insecte-update.component';
import { ImageInsecteService } from 'app/entities/image-insecte/image-insecte.service';
import { ImageInsecte } from 'app/shared/model/image-insecte.model';

describe('Component Tests', () => {
    describe('ImageInsecte Management Update Component', () => {
        let comp: ImageInsecteUpdateComponent;
        let fixture: ComponentFixture<ImageInsecteUpdateComponent>;
        let service: ImageInsecteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageInsecteUpdateComponent]
            })
                .overrideTemplate(ImageInsecteUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImageInsecteUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageInsecteService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ImageInsecte(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.imageInsecte = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ImageInsecte();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.imageInsecte = entity;
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
