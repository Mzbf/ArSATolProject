/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { InsecteRavageurDetailComponent } from 'app/entities/insecte-ravageur/insecte-ravageur-detail.component';
import { InsecteRavageur } from 'app/shared/model/insecte-ravageur.model';

describe('Component Tests', () => {
    describe('InsecteRavageur Management Detail Component', () => {
        let comp: InsecteRavageurDetailComponent;
        let fixture: ComponentFixture<InsecteRavageurDetailComponent>;
        const route = ({ data: of({ insecteRavageur: new InsecteRavageur(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [InsecteRavageurDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(InsecteRavageurDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InsecteRavageurDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.insecteRavageur).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
