import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IImageEnvoye } from 'app/shared/model/image-envoye.model';

type EntityResponseType = HttpResponse<IImageEnvoye>;
type EntityArrayResponseType = HttpResponse<IImageEnvoye[]>;

@Injectable({ providedIn: 'root' })
export class ImageEnvoyeService {
    public resourceUrl = SERVER_API_URL + 'api/image-envoyes';

    constructor(protected http: HttpClient) {}

    create(imageEnvoye: IImageEnvoye): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(imageEnvoye);
        return this.http
            .post<IImageEnvoye>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(imageEnvoye: IImageEnvoye): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(imageEnvoye);
        return this.http
            .put<IImageEnvoye>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IImageEnvoye>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IImageEnvoye[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(imageEnvoye: IImageEnvoye): IImageEnvoye {
        const copy: IImageEnvoye = Object.assign({}, imageEnvoye, {
            dateDAjout: imageEnvoye.dateDAjout != null && imageEnvoye.dateDAjout.isValid() ? imageEnvoye.dateDAjout.toJSON() : null,
            dateValidation:
                imageEnvoye.dateValidation != null && imageEnvoye.dateValidation.isValid() ? imageEnvoye.dateValidation.toJSON() : null
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
            res.body.forEach((imageEnvoye: IImageEnvoye) => {
                imageEnvoye.dateDAjout = imageEnvoye.dateDAjout != null ? moment(imageEnvoye.dateDAjout) : null;
                imageEnvoye.dateValidation = imageEnvoye.dateValidation != null ? moment(imageEnvoye.dateValidation) : null;
            });
        }
        return res;
    }
}
