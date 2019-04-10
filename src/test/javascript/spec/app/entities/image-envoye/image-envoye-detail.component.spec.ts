/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageEnvoyeDetailComponent } from 'app/entities/image-envoye/image-envoye-detail.component';
import { ImageEnvoye } from 'app/shared/model/image-envoye.model';

describe('Component Tests', () => {
    describe('ImageEnvoye Management Detail Component', () => {
        let comp: ImageEnvoyeDetailComponent;
        let fixture: ComponentFixture<ImageEnvoyeDetailComponent>;
        const route = ({ data: of({ imageEnvoye: new ImageEnvoye(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageEnvoyeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ImageEnvoyeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImageEnvoyeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.imageEnvoye).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
