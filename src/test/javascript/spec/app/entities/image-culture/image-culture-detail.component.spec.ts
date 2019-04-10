/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageCultureDetailComponent } from 'app/entities/image-culture/image-culture-detail.component';
import { ImageCulture } from 'app/shared/model/image-culture.model';

describe('Component Tests', () => {
    describe('ImageCulture Management Detail Component', () => {
        let comp: ImageCultureDetailComponent;
        let fixture: ComponentFixture<ImageCultureDetailComponent>;
        const route = ({ data: of({ imageCulture: new ImageCulture(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageCultureDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ImageCultureDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImageCultureDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.imageCulture).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
