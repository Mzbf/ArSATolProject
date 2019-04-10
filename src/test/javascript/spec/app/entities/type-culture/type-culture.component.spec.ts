/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { TypeCultureComponent } from 'app/entities/type-culture/type-culture.component';
import { TypeCultureService } from 'app/entities/type-culture/type-culture.service';
import { TypeCulture } from 'app/shared/model/type-culture.model';

describe('Component Tests', () => {
    describe('TypeCulture Management Component', () => {
        let comp: TypeCultureComponent;
        let fixture: ComponentFixture<TypeCultureComponent>;
        let service: TypeCultureService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [TypeCultureComponent],
                providers: []
            })
                .overrideTemplate(TypeCultureComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeCultureComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeCultureService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TypeCulture(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.typeCultures[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
