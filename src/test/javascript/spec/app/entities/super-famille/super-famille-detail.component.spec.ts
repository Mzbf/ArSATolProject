/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { SuperFamilleDetailComponent } from 'app/entities/super-famille/super-famille-detail.component';
import { SuperFamille } from 'app/shared/model/super-famille.model';

describe('Component Tests', () => {
    describe('SuperFamille Management Detail Component', () => {
        let comp: SuperFamilleDetailComponent;
        let fixture: ComponentFixture<SuperFamilleDetailComponent>;
        const route = ({ data: of({ superFamille: new SuperFamille(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [SuperFamilleDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SuperFamilleDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SuperFamilleDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.superFamille).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
