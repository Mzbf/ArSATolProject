/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ChercheurDetailComponent } from 'app/entities/chercheur/chercheur-detail.component';
import { Chercheur } from 'app/shared/model/chercheur.model';

describe('Component Tests', () => {
    describe('Chercheur Management Detail Component', () => {
        let comp: ChercheurDetailComponent;
        let fixture: ComponentFixture<ChercheurDetailComponent>;
        const route = ({ data: of({ chercheur: new Chercheur(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ChercheurDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ChercheurDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ChercheurDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.chercheur).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
