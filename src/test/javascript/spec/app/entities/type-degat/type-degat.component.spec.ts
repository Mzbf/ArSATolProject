/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { TypeDegatComponent } from 'app/entities/type-degat/type-degat.component';
import { TypeDegatService } from 'app/entities/type-degat/type-degat.service';
import { TypeDegat } from 'app/shared/model/type-degat.model';

describe('Component Tests', () => {
    describe('TypeDegat Management Component', () => {
        let comp: TypeDegatComponent;
        let fixture: ComponentFixture<TypeDegatComponent>;
        let service: TypeDegatService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [TypeDegatComponent],
                providers: []
            })
                .overrideTemplate(TypeDegatComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeDegatComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeDegatService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TypeDegat(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.typeDegats[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
