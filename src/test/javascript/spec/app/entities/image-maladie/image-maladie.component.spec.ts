/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageMaladieComponent } from 'app/entities/image-maladie/image-maladie.component';
import { ImageMaladieService } from 'app/entities/image-maladie/image-maladie.service';
import { ImageMaladie } from 'app/shared/model/image-maladie.model';

describe('Component Tests', () => {
    describe('ImageMaladie Management Component', () => {
        let comp: ImageMaladieComponent;
        let fixture: ComponentFixture<ImageMaladieComponent>;
        let service: ImageMaladieService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageMaladieComponent],
                providers: []
            })
                .overrideTemplate(ImageMaladieComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImageMaladieComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageMaladieService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ImageMaladie(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.imageMaladies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
