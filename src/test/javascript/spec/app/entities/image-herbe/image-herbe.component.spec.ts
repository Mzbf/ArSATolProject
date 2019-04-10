/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageHerbeComponent } from 'app/entities/image-herbe/image-herbe.component';
import { ImageHerbeService } from 'app/entities/image-herbe/image-herbe.service';
import { ImageHerbe } from 'app/shared/model/image-herbe.model';

describe('Component Tests', () => {
    describe('ImageHerbe Management Component', () => {
        let comp: ImageHerbeComponent;
        let fixture: ComponentFixture<ImageHerbeComponent>;
        let service: ImageHerbeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageHerbeComponent],
                providers: []
            })
                .overrideTemplate(ImageHerbeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImageHerbeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageHerbeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ImageHerbe(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.imageHerbes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
