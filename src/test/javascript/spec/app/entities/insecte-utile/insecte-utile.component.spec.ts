/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { InsecteUtileComponent } from 'app/entities/insecte-utile/insecte-utile.component';
import { InsecteUtileService } from 'app/entities/insecte-utile/insecte-utile.service';
import { InsecteUtile } from 'app/shared/model/insecte-utile.model';

describe('Component Tests', () => {
    describe('InsecteUtile Management Component', () => {
        let comp: InsecteUtileComponent;
        let fixture: ComponentFixture<InsecteUtileComponent>;
        let service: InsecteUtileService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [InsecteUtileComponent],
                providers: []
            })
                .overrideTemplate(InsecteUtileComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InsecteUtileComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InsecteUtileService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new InsecteUtile(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.insecteUtiles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
