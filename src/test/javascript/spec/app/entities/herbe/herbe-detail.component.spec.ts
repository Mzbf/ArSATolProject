/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { HerbeDetailComponent } from 'app/entities/herbe/herbe-detail.component';
import { Herbe } from 'app/shared/model/herbe.model';

describe('Component Tests', () => {
    describe('Herbe Management Detail Component', () => {
        let comp: HerbeDetailComponent;
        let fixture: ComponentFixture<HerbeDetailComponent>;
        const route = ({ data: of({ herbe: new Herbe(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [HerbeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(HerbeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(HerbeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.herbe).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
