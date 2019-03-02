/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { AdminInsecteDetailComponent } from 'app/entities/admin-insecte/admin-insecte-detail.component';
import { AdminInsecte } from 'app/shared/model/admin-insecte.model';

describe('Component Tests', () => {
    describe('AdminInsecte Management Detail Component', () => {
        let comp: AdminInsecteDetailComponent;
        let fixture: ComponentFixture<AdminInsecteDetailComponent>;
        const route = ({ data: of({ adminInsecte: new AdminInsecte(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [AdminInsecteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AdminInsecteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AdminInsecteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.adminInsecte).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
