/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ChercheurComponent } from 'app/entities/chercheur/chercheur.component';
import { ChercheurService } from 'app/entities/chercheur/chercheur.service';
import { Chercheur } from 'app/shared/model/chercheur.model';

describe('Component Tests', () => {
    describe('Chercheur Management Component', () => {
        let comp: ChercheurComponent;
        let fixture: ComponentFixture<ChercheurComponent>;
        let service: ChercheurService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ChercheurComponent],
                providers: []
            })
                .overrideTemplate(ChercheurComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ChercheurComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChercheurService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Chercheur(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.chercheurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
