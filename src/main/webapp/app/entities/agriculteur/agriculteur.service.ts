import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAgriculteur } from 'app/shared/model/agriculteur.model';

type EntityResponseType = HttpResponse<IAgriculteur>;
type EntityArrayResponseType = HttpResponse<IAgriculteur[]>;

@Injectable({ providedIn: 'root' })
export class AgriculteurService {
    public resourceUrl = SERVER_API_URL + 'api/agriculteurs';

    constructor(protected http: HttpClient) {}

    create(agriculteur: IAgriculteur): Observable<EntityResponseType> {
        return this.http.post<IAgriculteur>(this.resourceUrl, agriculteur, { observe: 'response' });
    }

    update(agriculteur: IAgriculteur): Observable<EntityResponseType> {
        return this.http.put<IAgriculteur>(this.resourceUrl, agriculteur, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAgriculteur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAgriculteur[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
