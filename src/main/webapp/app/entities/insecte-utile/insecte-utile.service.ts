import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInsecteUtile } from 'app/shared/model/insecte-utile.model';

type EntityResponseType = HttpResponse<IInsecteUtile>;
type EntityArrayResponseType = HttpResponse<IInsecteUtile[]>;

@Injectable({ providedIn: 'root' })
export class InsecteUtileService {
    public resourceUrl = SERVER_API_URL + 'api/insecte-utiles';

    constructor(protected http: HttpClient) {}

    create(insecteUtile: IInsecteUtile): Observable<EntityResponseType> {
        return this.http.post<IInsecteUtile>(this.resourceUrl, insecteUtile, { observe: 'response' });
    }

    update(insecteUtile: IInsecteUtile): Observable<EntityResponseType> {
        return this.http.put<IInsecteUtile>(this.resourceUrl, insecteUtile, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IInsecteUtile>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IInsecteUtile[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
