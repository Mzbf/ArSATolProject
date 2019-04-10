/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ZoneGeoComponent } from 'app/entities/zone-geo/zone-geo.component';
import { ZoneGeoService } from 'app/entities/zone-geo/zone-geo.service';
import { ZoneGeo } from 'app/shared/model/zone-geo.model';

describe('Component Tests', () => {
    describe('ZoneGeo Management Component', () => {
        let comp: ZoneGeoComponent;
        let fixture: ComponentFixture<ZoneGeoComponent>;
        let service: ZoneGeoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ZoneGeoComponent],
                providers: []
            })
                .overrideTemplate(ZoneGeoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ZoneGeoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ZoneGeoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ZoneGeo(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.zoneGeos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
