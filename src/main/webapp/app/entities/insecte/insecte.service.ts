import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInsecte } from 'app/shared/model/insecte.model';

type EntityResponseType = HttpResponse<IInsecte>;
type EntityArrayResponseType = HttpResponse<IInsecte[]>;

@Injectable({ providedIn: 'root' })
export class InsecteService {
    public resourceUrl = SERVER_API_URL + 'api/insectes';

    constructor(protected http: HttpClient) {}

    create(insecte: IInsecte): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(insecte);
        return this.http
            .post<IInsecte>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(insecte: IInsecte): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(insecte);
        return this.http
            .put<IInsecte>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IInsecte>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IInsecte[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(insecte: IInsecte): IInsecte {
        const copy: IInsecte = Object.assign({}, insecte, {
            dateValidation: insecte.dateValidation != null && insecte.dateValidation.isValid() ? insecte.dateValidation.toJSON() : null,
            dateAjout: insecte.dateAjout != null && insecte.dateAjout.isValid() ? insecte.dateAjout.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateValidation = res.body.dateValidation != null ? moment(res.body.dateValidation) : null;
            res.body.dateAjout = res.body.dateAjout != null ? moment(res.body.dateAjout) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((insecte: IInsecte) => {
                insecte.dateValidation = insecte.dateValidation != null ? moment(insecte.dateValidation) : null;
                insecte.dateAjout = insecte.dateAjout != null ? moment(insecte.dateAjout) : null;
            });
        }
        return res;
    }
}
