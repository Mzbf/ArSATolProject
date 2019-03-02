/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { InsecteDetailComponent } from 'app/entities/insecte/insecte-detail.component';
import { Insecte } from 'app/shared/model/insecte.model';

describe('Component Tests', () => {
    describe('Insecte Management Detail Component', () => {
        let comp: InsecteDetailComponent;
        let fixture: ComponentFixture<InsecteDetailComponent>;
        const route = ({ data: of({ insecte: new Insecte(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [InsecteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(InsecteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InsecteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.insecte).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
