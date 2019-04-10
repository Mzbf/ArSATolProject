/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageCultureComponent } from 'app/entities/image-culture/image-culture.component';
import { ImageCultureService } from 'app/entities/image-culture/image-culture.service';
import { ImageCulture } from 'app/shared/model/image-culture.model';

describe('Component Tests', () => {
    describe('ImageCulture Management Component', () => {
        let comp: ImageCultureComponent;
        let fixture: ComponentFixture<ImageCultureComponent>;
        let service: ImageCultureService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageCultureComponent],
                providers: []
            })
                .overrideTemplate(ImageCultureComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImageCultureComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageCultureService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ImageCulture(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.imageCultures[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
