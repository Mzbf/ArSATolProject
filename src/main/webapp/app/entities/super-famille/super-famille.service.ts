import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISuperFamille } from 'app/shared/model/super-famille.model';

type EntityResponseType = HttpResponse<ISuperFamille>;
type EntityArrayResponseType = HttpResponse<ISuperFamille[]>;

@Injectable({ providedIn: 'root' })
export class SuperFamilleService {
    public resourceUrl = SERVER_API_URL + 'api/super-familles';

    constructor(protected http: HttpClient) {}

    create(superFamille: ISuperFamille): Observable<EntityResponseType> {
        return this.http.post<ISuperFamille>(this.resourceUrl, superFamille, { observe: 'response' });
    }

    update(superFamille: ISuperFamille): Observable<EntityResponseType> {
        return this.http.put<ISuperFamille>(this.resourceUrl, superFamille, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISuperFamille>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISuperFamille[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
