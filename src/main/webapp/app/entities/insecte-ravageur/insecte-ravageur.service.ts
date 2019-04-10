import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInsecteRavageur } from 'app/shared/model/insecte-ravageur.model';

type EntityResponseType = HttpResponse<IInsecteRavageur>;
type EntityArrayResponseType = HttpResponse<IInsecteRavageur[]>;

@Injectable({ providedIn: 'root' })
export class InsecteRavageurService {
    public resourceUrl = SERVER_API_URL + 'api/insecte-ravageurs';

    constructor(protected http: HttpClient) {}

    create(insecteRavageur: IInsecteRavageur): Observable<EntityResponseType> {
        return this.http.post<IInsecteRavageur>(this.resourceUrl, insecteRavageur, { observe: 'response' });
    }

    update(insecteRavageur: IInsecteRavageur): Observable<EntityResponseType> {
        return this.http.put<IInsecteRavageur>(this.resourceUrl, insecteRavageur, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IInsecteRavageur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IInsecteRavageur[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
