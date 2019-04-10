/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageAttaqueDetailComponent } from 'app/entities/image-attaque/image-attaque-detail.component';
import { ImageAttaque } from 'app/shared/model/image-attaque.model';

describe('Component Tests', () => {
    describe('ImageAttaque Management Detail Component', () => {
        let comp: ImageAttaqueDetailComponent;
        let fixture: ComponentFixture<ImageAttaqueDetailComponent>;
        const route = ({ data: of({ imageAttaque: new ImageAttaque(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageAttaqueDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ImageAttaqueDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImageAttaqueDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.imageAttaque).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
