/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { InsecteUtileDetailComponent } from 'app/entities/insecte-utile/insecte-utile-detail.component';
import { InsecteUtile } from 'app/shared/model/insecte-utile.model';

describe('Component Tests', () => {
    describe('InsecteUtile Management Detail Component', () => {
        let comp: InsecteUtileDetailComponent;
        let fixture: ComponentFixture<InsecteUtileDetailComponent>;
        const route = ({ data: of({ insecteUtile: new InsecteUtile(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [InsecteUtileDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(InsecteUtileDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InsecteUtileDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.insecteUtile).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
