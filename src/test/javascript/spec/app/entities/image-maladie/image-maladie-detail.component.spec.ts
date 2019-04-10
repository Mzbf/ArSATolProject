/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageMaladieDetailComponent } from 'app/entities/image-maladie/image-maladie-detail.component';
import { ImageMaladie } from 'app/shared/model/image-maladie.model';

describe('Component Tests', () => {
    describe('ImageMaladie Management Detail Component', () => {
        let comp: ImageMaladieDetailComponent;
        let fixture: ComponentFixture<ImageMaladieDetailComponent>;
        const route = ({ data: of({ imageMaladie: new ImageMaladie(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageMaladieDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ImageMaladieDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImageMaladieDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.imageMaladie).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
