import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITypeDegat } from 'app/shared/model/type-degat.model';

type EntityResponseType = HttpResponse<ITypeDegat>;
type EntityArrayResponseType = HttpResponse<ITypeDegat[]>;

@Injectable({ providedIn: 'root' })
export class TypeDegatService {
    public resourceUrl = SERVER_API_URL + 'api/type-degats';

    constructor(protected http: HttpClient) {}

    create(typeDegat: ITypeDegat): Observable<EntityResponseType> {
        return this.http.post<ITypeDegat>(this.resourceUrl, typeDegat, { observe: 'response' });
    }

    update(typeDegat: ITypeDegat): Observable<EntityResponseType> {
        return this.http.put<ITypeDegat>(this.resourceUrl, typeDegat, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITypeDegat>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITypeDegat[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
