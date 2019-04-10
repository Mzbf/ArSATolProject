/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { InsecteComponent } from 'app/entities/insecte/insecte.component';
import { InsecteService } from 'app/entities/insecte/insecte.service';
import { Insecte } from 'app/shared/model/insecte.model';

describe('Component Tests', () => {
    describe('Insecte Management Component', () => {
        let comp: InsecteComponent;
        let fixture: ComponentFixture<InsecteComponent>;
        let service: InsecteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [InsecteComponent],
                providers: []
            })
                .overrideTemplate(InsecteComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InsecteComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InsecteService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Insecte(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.insectes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
