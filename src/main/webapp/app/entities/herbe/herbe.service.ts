import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHerbe } from 'app/shared/model/herbe.model';

type EntityResponseType = HttpResponse<IHerbe>;
type EntityArrayResponseType = HttpResponse<IHerbe[]>;

@Injectable({ providedIn: 'root' })
export class HerbeService {
    public resourceUrl = SERVER_API_URL + 'api/herbes';

    constructor(protected http: HttpClient) {}

    create(herbe: IHerbe): Observable<EntityResponseType> {
        return this.http.post<IHerbe>(this.resourceUrl, herbe, { observe: 'response' });
    }

    update(herbe: IHerbe): Observable<EntityResponseType> {
        return this.http.put<IHerbe>(this.resourceUrl, herbe, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IHerbe>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IHerbe[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
