/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { AgriculteurDetailComponent } from 'app/entities/agriculteur/agriculteur-detail.component';
import { Agriculteur } from 'app/shared/model/agriculteur.model';

describe('Component Tests', () => {
    describe('Agriculteur Management Detail Component', () => {
        let comp: AgriculteurDetailComponent;
        let fixture: ComponentFixture<AgriculteurDetailComponent>;
        const route = ({ data: of({ agriculteur: new Agriculteur(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [AgriculteurDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AgriculteurDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AgriculteurDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.agriculteur).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
