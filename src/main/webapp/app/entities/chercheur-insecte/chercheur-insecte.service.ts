import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IChercheurInsecte } from 'app/shared/model/chercheur-insecte.model';

type EntityResponseType = HttpResponse<IChercheurInsecte>;
type EntityArrayResponseType = HttpResponse<IChercheurInsecte[]>;

@Injectable({ providedIn: 'root' })
export class ChercheurInsecteService {
    public resourceUrl = SERVER_API_URL + 'api/chercheur-insectes';

    constructor(protected http: HttpClient) {}

    create(chercheurInsecte: IChercheurInsecte): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(chercheurInsecte);
        return this.http
            .post<IChercheurInsecte>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(chercheurInsecte: IChercheurInsecte): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(chercheurInsecte);
        return this.http
            .put<IChercheurInsecte>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IChercheurInsecte>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IChercheurInsecte[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(chercheurInsecte: IChercheurInsecte): IChercheurInsecte {
        const copy: IChercheurInsecte = Object.assign({}, chercheurInsecte, {
            dateDAjout:
                chercheurInsecte.dateDAjout != null && chercheurInsecte.dateDAjout.isValid() ? chercheurInsecte.dateDAjout.toJSON() : null,
            dateValidation:
                chercheurInsecte.dateValidation != null && chercheurInsecte.dateValidation.isValid()
                    ? chercheurInsecte.dateValidation.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateDAjout = res.body.dateDAjout != null ? moment(res.body.dateDAjout) : null;
            res.body.dateValidation = res.body.dateValidation != null ? moment(res.body.dateValidation) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((chercheurInsecte: IChercheurInsecte) => {
                chercheurInsecte.dateDAjout = chercheurInsecte.dateDAjout != null ? moment(chercheurInsecte.dateDAjout) : null;
                chercheurInsecte.dateValidation = chercheurInsecte.dateValidation != null ? moment(chercheurInsecte.dateValidation) : null;
            });
        }
        return res;
    }
}
