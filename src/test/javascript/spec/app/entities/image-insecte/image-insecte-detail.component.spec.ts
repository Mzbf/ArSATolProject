/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageInsecteDetailComponent } from 'app/entities/image-insecte/image-insecte-detail.component';
import { ImageInsecte } from 'app/shared/model/image-insecte.model';

describe('Component Tests', () => {
    describe('ImageInsecte Management Detail Component', () => {
        let comp: ImageInsecteDetailComponent;
        let fixture: ComponentFixture<ImageInsecteDetailComponent>;
        const route = ({ data: of({ imageInsecte: new ImageInsecte(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageInsecteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ImageInsecteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImageInsecteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.imageInsecte).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
