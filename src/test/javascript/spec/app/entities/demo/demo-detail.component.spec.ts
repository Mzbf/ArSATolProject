/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { DemoDetailComponent } from 'app/entities/demo/demo-detail.component';
import { Demo } from 'app/shared/model/demo.model';

describe('Component Tests', () => {
    describe('Demo Management Detail Component', () => {
        let comp: DemoDetailComponent;
        let fixture: ComponentFixture<DemoDetailComponent>;
        const route = ({ data: of({ demo: new Demo(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [DemoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DemoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DemoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.demo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
