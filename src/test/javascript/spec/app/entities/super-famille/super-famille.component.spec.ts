/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { SuperFamilleComponent } from 'app/entities/super-famille/super-famille.component';
import { SuperFamilleService } from 'app/entities/super-famille/super-famille.service';
import { SuperFamille } from 'app/shared/model/super-famille.model';

describe('Component Tests', () => {
    describe('SuperFamille Management Component', () => {
        let comp: SuperFamilleComponent;
        let fixture: ComponentFixture<SuperFamilleComponent>;
        let service: SuperFamilleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [SuperFamilleComponent],
                providers: []
            })
                .overrideTemplate(SuperFamilleComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SuperFamilleComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SuperFamilleService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SuperFamille(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.superFamilles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
