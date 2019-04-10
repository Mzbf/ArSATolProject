import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IZoneGeo } from 'app/shared/model/zone-geo.model';

type EntityResponseType = HttpResponse<IZoneGeo>;
type EntityArrayResponseType = HttpResponse<IZoneGeo[]>;

@Injectable({ providedIn: 'root' })
export class ZoneGeoService {
    public resourceUrl = SERVER_API_URL + 'api/zone-geos';

    constructor(protected http: HttpClient) {}

    create(zoneGeo: IZoneGeo): Observable<EntityResponseType> {
        return this.http.post<IZoneGeo>(this.resourceUrl, zoneGeo, { observe: 'response' });
    }

    update(zoneGeo: IZoneGeo): Observable<EntityResponseType> {
        return this.http.put<IZoneGeo>(this.resourceUrl, zoneGeo, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IZoneGeo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IZoneGeo[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
