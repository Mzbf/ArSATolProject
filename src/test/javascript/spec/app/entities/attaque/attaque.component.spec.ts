/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { AttaqueComponent } from 'app/entities/attaque/attaque.component';
import { AttaqueService } from 'app/entities/attaque/attaque.service';
import { Attaque } from 'app/shared/model/attaque.model';

describe('Component Tests', () => {
    describe('Attaque Management Component', () => {
        let comp: AttaqueComponent;
        let fixture: ComponentFixture<AttaqueComponent>;
        let service: AttaqueService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [AttaqueComponent],
                providers: []
            })
                .overrideTemplate(AttaqueComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AttaqueComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttaqueService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Attaque(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.attaques[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
