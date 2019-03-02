/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ChercheurInsecteDetailComponent } from 'app/entities/chercheur-insecte/chercheur-insecte-detail.component';
import { ChercheurInsecte } from 'app/shared/model/chercheur-insecte.model';

describe('Component Tests', () => {
    describe('ChercheurInsecte Management Detail Component', () => {
        let comp: ChercheurInsecteDetailComponent;
        let fixture: ComponentFixture<ChercheurInsecteDetailComponent>;
        const route = ({ data: of({ chercheurInsecte: new ChercheurInsecte(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ChercheurInsecteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ChercheurInsecteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ChercheurInsecteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.chercheurInsecte).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
