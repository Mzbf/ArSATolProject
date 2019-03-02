import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IImageInsecte } from 'app/shared/model/image-insecte.model';

type EntityResponseType = HttpResponse<IImageInsecte>;
type EntityArrayResponseType = HttpResponse<IImageInsecte[]>;

@Injectable({ providedIn: 'root' })
export class ImageInsecteService {
    public resourceUrl = SERVER_API_URL + 'api/image-insectes';

    constructor(protected http: HttpClient) {}

    create(imageInsecte: IImageInsecte): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(imageInsecte);
        return this.http
            .post<IImageInsecte>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(imageInsecte: IImageInsecte): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(imageInsecte);
        return this.http
            .put<IImageInsecte>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IImageInsecte>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IImageInsecte[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(imageInsecte: IImageInsecte): IImageInsecte {
        const copy: IImageInsecte = Object.assign({}, imageInsecte, {
            dateDAjout: imageInsecte.dateDAjout != null && imageInsecte.dateDAjout.isValid() ? imageInsecte.dateDAjout.toJSON() : null,
            dateValidation:
                imageInsecte.dateValidation != null && imageInsecte.dateValidation.isValid() ? imageInsecte.dateValidation.toJSON() : null
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
            res.body.forEach((imageInsecte: IImageInsecte) => {
                imageInsecte.dateDAjout = imageInsecte.dateDAjout != null ? moment(imageInsecte.dateDAjout) : null;
                imageInsecte.dateValidation = imageInsecte.dateValidation != null ? moment(imageInsecte.dateValidation) : null;
            });
        }
        return res;
    }
}
