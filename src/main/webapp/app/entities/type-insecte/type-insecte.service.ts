import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITypeInsecte } from 'app/shared/model/type-insecte.model';

type EntityResponseType = HttpResponse<ITypeInsecte>;
type EntityArrayResponseType = HttpResponse<ITypeInsecte[]>;

@Injectable({ providedIn: 'root' })
export class TypeInsecteService {
    public resourceUrl = SERVER_API_URL + 'api/type-insectes';

    constructor(protected http: HttpClient) {}

    create(typeInsecte: ITypeInsecte): Observable<EntityResponseType> {
        return this.http.post<ITypeInsecte>(this.resourceUrl, typeInsecte, { observe: 'response' });
    }

    update(typeInsecte: ITypeInsecte): Observable<EntityResponseType> {
        return this.http.put<ITypeInsecte>(this.resourceUrl, typeInsecte, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITypeInsecte>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITypeInsecte[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
