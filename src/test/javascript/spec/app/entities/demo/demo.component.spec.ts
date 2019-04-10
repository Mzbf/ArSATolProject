/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { DemoComponent } from 'app/entities/demo/demo.component';
import { DemoService } from 'app/entities/demo/demo.service';
import { Demo } from 'app/shared/model/demo.model';

describe('Component Tests', () => {
    describe('Demo Management Component', () => {
        let comp: DemoComponent;
        let fixture: ComponentFixture<DemoComponent>;
        let service: DemoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [DemoComponent],
                providers: []
            })
                .overrideTemplate(DemoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DemoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DemoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Demo(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.demos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
