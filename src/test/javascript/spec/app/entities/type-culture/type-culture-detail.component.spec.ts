/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { TypeCultureDetailComponent } from 'app/entities/type-culture/type-culture-detail.component';
import { TypeCulture } from 'app/shared/model/type-culture.model';

describe('Component Tests', () => {
    describe('TypeCulture Management Detail Component', () => {
        let comp: TypeCultureDetailComponent;
        let fixture: ComponentFixture<TypeCultureDetailComponent>;
        const route = ({ data: of({ typeCulture: new TypeCulture(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [TypeCultureDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TypeCultureDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeCultureDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.typeCulture).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
