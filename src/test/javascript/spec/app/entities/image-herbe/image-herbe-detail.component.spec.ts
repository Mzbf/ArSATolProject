/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageHerbeDetailComponent } from 'app/entities/image-herbe/image-herbe-detail.component';
import { ImageHerbe } from 'app/shared/model/image-herbe.model';

describe('Component Tests', () => {
    describe('ImageHerbe Management Detail Component', () => {
        let comp: ImageHerbeDetailComponent;
        let fixture: ComponentFixture<ImageHerbeDetailComponent>;
        const route = ({ data: of({ imageHerbe: new ImageHerbe(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageHerbeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ImageHerbeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImageHerbeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.imageHerbe).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
