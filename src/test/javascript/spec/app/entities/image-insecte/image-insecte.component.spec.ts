/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageInsecteComponent } from 'app/entities/image-insecte/image-insecte.component';
import { ImageInsecteService } from 'app/entities/image-insecte/image-insecte.service';
import { ImageInsecte } from 'app/shared/model/image-insecte.model';

describe('Component Tests', () => {
    describe('ImageInsecte Management Component', () => {
        let comp: ImageInsecteComponent;
        let fixture: ComponentFixture<ImageInsecteComponent>;
        let service: ImageInsecteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageInsecteComponent],
                providers: []
            })
                .overrideTemplate(ImageInsecteComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImageInsecteComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageInsecteService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ImageInsecte(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.imageInsectes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
