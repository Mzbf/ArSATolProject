import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAdminInsecte } from 'app/shared/model/admin-insecte.model';

type EntityResponseType = HttpResponse<IAdminInsecte>;
type EntityArrayResponseType = HttpResponse<IAdminInsecte[]>;

@Injectable({ providedIn: 'root' })
export class AdminInsecteService {
    public resourceUrl = SERVER_API_URL + 'api/admin-insectes';

    constructor(protected http: HttpClient) {}

    create(adminInsecte: IAdminInsecte): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(adminInsecte);
        return this.http
            .post<IAdminInsecte>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(adminInsecte: IAdminInsecte): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(adminInsecte);
        return this.http
            .put<IAdminInsecte>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAdminInsecte>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAdminInsecte[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(adminInsecte: IAdminInsecte): IAdminInsecte {
        const copy: IAdminInsecte = Object.assign({}, adminInsecte, {
            dateDAjout: adminInsecte.dateDAjout != null && adminInsecte.dateDAjout.isValid() ? adminInsecte.dateDAjout.toJSON() : null,
            dateValidation:
                adminInsecte.dateValidation != null && adminInsecte.dateValidation.isValid() ? adminInsecte.dateValidation.toJSON() : null
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
            res.body.forEach((adminInsecte: IAdminInsecte) => {
                adminInsecte.dateDAjout = adminInsecte.dateDAjout != null ? moment(adminInsecte.dateDAjout) : null;
                adminInsecte.dateValidation = adminInsecte.dateValidation != null ? moment(adminInsecte.dateValidation) : null;
            });
        }
        return res;
    }
}
