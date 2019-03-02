/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { AttaqueDetailComponent } from 'app/entities/attaque/attaque-detail.component';
import { Attaque } from 'app/shared/model/attaque.model';

describe('Component Tests', () => {
    describe('Attaque Management Detail Component', () => {
        let comp: AttaqueDetailComponent;
        let fixture: ComponentFixture<AttaqueDetailComponent>;
        const route = ({ data: of({ attaque: new Attaque(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [AttaqueDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AttaqueDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AttaqueDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.attaque).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
