/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { MaladieComponent } from 'app/entities/maladie/maladie.component';
import { MaladieService } from 'app/entities/maladie/maladie.service';
import { Maladie } from 'app/shared/model/maladie.model';

describe('Component Tests', () => {
    describe('Maladie Management Component', () => {
        let comp: MaladieComponent;
        let fixture: ComponentFixture<MaladieComponent>;
        let service: MaladieService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [MaladieComponent],
                providers: []
            })
                .overrideTemplate(MaladieComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MaladieComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MaladieService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Maladie(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.maladies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
