/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { TypeInsecteDetailComponent } from 'app/entities/type-insecte/type-insecte-detail.component';
import { TypeInsecte } from 'app/shared/model/type-insecte.model';

describe('Component Tests', () => {
    describe('TypeInsecte Management Detail Component', () => {
        let comp: TypeInsecteDetailComponent;
        let fixture: ComponentFixture<TypeInsecteDetailComponent>;
        const route = ({ data: of({ typeInsecte: new TypeInsecte(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [TypeInsecteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TypeInsecteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeInsecteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.typeInsecte).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
