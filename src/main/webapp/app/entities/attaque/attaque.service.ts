import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAttaque } from 'app/shared/model/attaque.model';

type EntityResponseType = HttpResponse<IAttaque>;
type EntityArrayResponseType = HttpResponse<IAttaque[]>;

@Injectable({ providedIn: 'root' })
export class AttaqueService {
    public resourceUrl = SERVER_API_URL + 'api/attaques';

    constructor(protected http: HttpClient) {}

    create(attaque: IAttaque): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(attaque);
        return this.http
            .post<IAttaque>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(attaque: IAttaque): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(attaque);
        return this.http
            .put<IAttaque>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAttaque>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAttaque[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(attaque: IAttaque): IAttaque {
        const copy: IAttaque = Object.assign({}, attaque, {
            dateValidation: attaque.dateValidation != null && attaque.dateValidation.isValid() ? attaque.dateValidation.toJSON() : null,
            dateAjout: attaque.dateAjout != null && attaque.dateAjout.isValid() ? attaque.dateAjout.toJSON() : null
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
            res.body.forEach((attaque: IAttaque) => {
                attaque.dateValidation = attaque.dateValidation != null ? moment(attaque.dateValidation) : null;
                attaque.dateAjout = attaque.dateAjout != null ? moment(attaque.dateAjout) : null;
            });
        }
        return res;
    }
}
