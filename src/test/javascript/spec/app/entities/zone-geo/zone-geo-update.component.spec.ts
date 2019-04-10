/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ArsatollserviceTestModule } from '../../../test.module';
import { ZoneGeoUpdateComponent } from 'app/entities/zone-geo/zone-geo-update.component';
import { ZoneGeoService } from 'app/entities/zone-geo/zone-geo.service';
import { ZoneGeo } from 'app/shared/model/zone-geo.model';

describe('Component Tests', () => {
    describe('ZoneGeo Management Update Component', () => {
        let comp: ZoneGeoUpdateComponent;
        let fixture: ComponentFixture<ZoneGeoUpdateComponent>;
        let service: ZoneGeoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ArsatollserviceTestModule],
                declarations: [ZoneGeoUpdateComponent]
            })
                .overrideTemplate(ZoneGeoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ZoneGeoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ZoneGeoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ZoneGeo(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.zoneGeo = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ZoneGeo();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.zoneGeo = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
