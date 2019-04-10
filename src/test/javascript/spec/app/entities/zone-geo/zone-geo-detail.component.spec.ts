/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ZoneGeoDetailComponent } from 'app/entities/zone-geo/zone-geo-detail.component';
import { ZoneGeo } from 'app/shared/model/zone-geo.model';

describe('Component Tests', () => {
    describe('ZoneGeo Management Detail Component', () => {
        let comp: ZoneGeoDetailComponent;
        let fixture: ComponentFixture<ZoneGeoDetailComponent>;
        const route = ({ data: of({ zoneGeo: new ZoneGeo(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ZoneGeoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ZoneGeoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ZoneGeoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.zoneGeo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
