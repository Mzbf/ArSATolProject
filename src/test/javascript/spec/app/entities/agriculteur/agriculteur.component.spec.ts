/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { AgriculteurComponent } from 'app/entities/agriculteur/agriculteur.component';
import { AgriculteurService } from 'app/entities/agriculteur/agriculteur.service';
import { Agriculteur } from 'app/shared/model/agriculteur.model';

describe('Component Tests', () => {
    describe('Agriculteur Management Component', () => {
        let comp: AgriculteurComponent;
        let fixture: ComponentFixture<AgriculteurComponent>;
        let service: AgriculteurService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [AgriculteurComponent],
                providers: []
            })
                .overrideTemplate(AgriculteurComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AgriculteurComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AgriculteurService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Agriculteur(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.agriculteurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
