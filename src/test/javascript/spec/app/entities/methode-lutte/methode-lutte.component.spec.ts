/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { MethodeLutteComponent } from 'app/entities/methode-lutte/methode-lutte.component';
import { MethodeLutteService } from 'app/entities/methode-lutte/methode-lutte.service';
import { MethodeLutte } from 'app/shared/model/methode-lutte.model';

describe('Component Tests', () => {
    describe('MethodeLutte Management Component', () => {
        let comp: MethodeLutteComponent;
        let fixture: ComponentFixture<MethodeLutteComponent>;
        let service: MethodeLutteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [MethodeLutteComponent],
                providers: []
            })
                .overrideTemplate(MethodeLutteComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MethodeLutteComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MethodeLutteService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new MethodeLutte(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.methodeLuttes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
