/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { TypeDegatDetailComponent } from 'app/entities/type-degat/type-degat-detail.component';
import { TypeDegat } from 'app/shared/model/type-degat.model';

describe('Component Tests', () => {
    describe('TypeDegat Management Detail Component', () => {
        let comp: TypeDegatDetailComponent;
        let fixture: ComponentFixture<TypeDegatDetailComponent>;
        const route = ({ data: of({ typeDegat: new TypeDegat(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [TypeDegatDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TypeDegatDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeDegatDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.typeDegat).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
