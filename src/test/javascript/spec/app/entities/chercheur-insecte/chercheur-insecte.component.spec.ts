/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ChercheurInsecteComponent } from 'app/entities/chercheur-insecte/chercheur-insecte.component';
import { ChercheurInsecteService } from 'app/entities/chercheur-insecte/chercheur-insecte.service';
import { ChercheurInsecte } from 'app/shared/model/chercheur-insecte.model';

describe('Component Tests', () => {
    describe('ChercheurInsecte Management Component', () => {
        let comp: ChercheurInsecteComponent;
        let fixture: ComponentFixture<ChercheurInsecteComponent>;
        let service: ChercheurInsecteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ChercheurInsecteComponent],
                providers: []
            })
                .overrideTemplate(ChercheurInsecteComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ChercheurInsecteComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChercheurInsecteService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ChercheurInsecte(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.chercheurInsectes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
