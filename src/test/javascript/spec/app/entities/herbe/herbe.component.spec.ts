/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { HerbeComponent } from 'app/entities/herbe/herbe.component';
import { HerbeService } from 'app/entities/herbe/herbe.service';
import { Herbe } from 'app/shared/model/herbe.model';

describe('Component Tests', () => {
    describe('Herbe Management Component', () => {
        let comp: HerbeComponent;
        let fixture: ComponentFixture<HerbeComponent>;
        let service: HerbeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [HerbeComponent],
                providers: []
            })
                .overrideTemplate(HerbeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(HerbeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HerbeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Herbe(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.herbes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
