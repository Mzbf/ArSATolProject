import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IChercheur } from 'app/shared/model/chercheur.model';

type EntityResponseType = HttpResponse<IChercheur>;
type EntityArrayResponseType = HttpResponse<IChercheur[]>;

@Injectable({ providedIn: 'root' })
export class ChercheurService {
    public resourceUrl = SERVER_API_URL + 'api/chercheurs';

    constructor(protected http: HttpClient) {}

    create(chercheur: IChercheur): Observable<EntityResponseType> {
        return this.http.post<IChercheur>(this.resourceUrl, chercheur, { observe: 'response' });
    }

    update(chercheur: IChercheur): Observable<EntityResponseType> {
        return this.http.put<IChercheur>(this.resourceUrl, chercheur, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IChercheur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IChercheur[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
