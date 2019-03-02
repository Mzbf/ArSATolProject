/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { AdminInsecteComponent } from 'app/entities/admin-insecte/admin-insecte.component';
import { AdminInsecteService } from 'app/entities/admin-insecte/admin-insecte.service';
import { AdminInsecte } from 'app/shared/model/admin-insecte.model';

describe('Component Tests', () => {
    describe('AdminInsecte Management Component', () => {
        let comp: AdminInsecteComponent;
        let fixture: ComponentFixture<AdminInsecteComponent>;
        let service: AdminInsecteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [AdminInsecteComponent],
                providers: []
            })
                .overrideTemplate(AdminInsecteComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AdminInsecteComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AdminInsecteService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AdminInsecte(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.adminInsectes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
