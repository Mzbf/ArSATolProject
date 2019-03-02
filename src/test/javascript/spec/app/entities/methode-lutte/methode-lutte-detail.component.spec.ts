/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { MethodeLutteDetailComponent } from 'app/entities/methode-lutte/methode-lutte-detail.component';
import { MethodeLutte } from 'app/shared/model/methode-lutte.model';

describe('Component Tests', () => {
    describe('MethodeLutte Management Detail Component', () => {
        let comp: MethodeLutteDetailComponent;
        let fixture: ComponentFixture<MethodeLutteDetailComponent>;
        const route = ({ data: of({ methodeLutte: new MethodeLutte(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [MethodeLutteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MethodeLutteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MethodeLutteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.methodeLutte).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
