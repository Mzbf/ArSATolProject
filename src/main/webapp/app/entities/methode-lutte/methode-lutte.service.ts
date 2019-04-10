import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMethodeLutte } from 'app/shared/model/methode-lutte.model';

type EntityResponseType = HttpResponse<IMethodeLutte>;
type EntityArrayResponseType = HttpResponse<IMethodeLutte[]>;

@Injectable({ providedIn: 'root' })
export class MethodeLutteService {
    public resourceUrl = SERVER_API_URL + 'api/methode-luttes';

    constructor(protected http: HttpClient) {}

    create(methodeLutte: IMethodeLutte): Observable<EntityResponseType> {
        return this.http.post<IMethodeLutte>(this.resourceUrl, methodeLutte, { observe: 'response' });
    }

    update(methodeLutte: IMethodeLutte): Observable<EntityResponseType> {
        return this.http.put<IMethodeLutte>(this.resourceUrl, methodeLutte, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMethodeLutte>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMethodeLutte[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
