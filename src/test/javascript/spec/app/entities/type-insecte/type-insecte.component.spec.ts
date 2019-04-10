/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { TypeInsecteComponent } from 'app/entities/type-insecte/type-insecte.component';
import { TypeInsecteService } from 'app/entities/type-insecte/type-insecte.service';
import { TypeInsecte } from 'app/shared/model/type-insecte.model';

describe('Component Tests', () => {
    describe('TypeInsecte Management Component', () => {
        let comp: TypeInsecteComponent;
        let fixture: ComponentFixture<TypeInsecteComponent>;
        let service: TypeInsecteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [TypeInsecteComponent],
                providers: []
            })
                .overrideTemplate(TypeInsecteComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeInsecteComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeInsecteService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TypeInsecte(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.typeInsectes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
