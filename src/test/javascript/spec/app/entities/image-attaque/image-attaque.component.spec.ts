/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ImageAttaqueComponent } from 'app/entities/image-attaque/image-attaque.component';
import { ImageAttaqueService } from 'app/entities/image-attaque/image-attaque.service';
import { ImageAttaque } from 'app/shared/model/image-attaque.model';

describe('Component Tests', () => {
    describe('ImageAttaque Management Component', () => {
        let comp: ImageAttaqueComponent;
        let fixture: ComponentFixture<ImageAttaqueComponent>;
        let service: ImageAttaqueService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ImageAttaqueComponent],
                providers: []
            })
                .overrideTemplate(ImageAttaqueComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImageAttaqueComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImageAttaqueService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ImageAttaque(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.imageAttaques[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
