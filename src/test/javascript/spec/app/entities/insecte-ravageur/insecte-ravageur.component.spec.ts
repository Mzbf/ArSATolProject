/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { InsecteRavageurComponent } from 'app/entities/insecte-ravageur/insecte-ravageur.component';
import { InsecteRavageurService } from 'app/entities/insecte-ravageur/insecte-ravageur.service';
import { InsecteRavageur } from 'app/shared/model/insecte-ravageur.model';

describe('Component Tests', () => {
    describe('InsecteRavageur Management Component', () => {
        let comp: InsecteRavageurComponent;
        let fixture: ComponentFixture<InsecteRavageurComponent>;
        let service: InsecteRavageurService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [InsecteRavageurComponent],
                providers: []
            })
                .overrideTemplate(InsecteRavageurComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InsecteRavageurComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InsecteRavageurService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new InsecteRavageur(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.insecteRavageurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
