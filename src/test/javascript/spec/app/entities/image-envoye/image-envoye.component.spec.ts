/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageEnvoyeComponent } from 'app/entities/image-envoye/image-envoye.component';
import { ImageEnvoyeService } from 'app/entities/image-envoye/image-envoye.service';
import { ImageEnvoye } from 'app/shared/model/image-envoye.model';

describe('Component Tests', () => {
    describe('ImageEnvoye Management Component', () => {
        let comp: ImageEnvoyeComponent;
        let fixture: ComponentFixture<ImageEnvoyeComponent>;
        let service: ImageEnvoyeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageEnvoyeComponent],
                providers: []
            })
                .overrideTemplate(ImageEnvoyeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImageEnvoyeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageEnvoyeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ImageEnvoye(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.imageEnvoyes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
