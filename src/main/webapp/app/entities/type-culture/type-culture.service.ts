import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITypeCulture } from 'app/shared/model/type-culture.model';

type EntityResponseType = HttpResponse<ITypeCulture>;
type EntityArrayResponseType = HttpResponse<ITypeCulture[]>;

@Injectable({ providedIn: 'root' })
export class TypeCultureService {
    public resourceUrl = SERVER_API_URL + 'api/type-cultures';

    constructor(protected http: HttpClient) {}

    create(typeCulture: ITypeCulture): Observable<EntityResponseType> {
        return this.http.post<ITypeCulture>(this.resourceUrl, typeCulture, { observe: 'response' });
    }

    update(typeCulture: ITypeCulture): Observable<EntityResponseType> {
        return this.http.put<ITypeCulture>(this.resourceUrl, typeCulture, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITypeCulture>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITypeCulture[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
