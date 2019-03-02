import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IImage } from 'app/shared/model/image.model';

type EntityResponseType = HttpResponse<IImage>;
type EntityArrayResponseType = HttpResponse<IImage[]>;

@Injectable({ providedIn: 'root' })
export class ImageService {
    public resourceUrl = SERVER_API_URL + 'api/images';

    constructor(protected http: HttpClient) {}

    create(image: IImage): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(image);
        return this.http
            .post<IImage>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(image: IImage): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(image);
        return this.http
            .put<IImage>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IImage>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IImage[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(image: IImage): IImage {
        const copy: IImage = Object.assign({}, image, {
            dateDAjout: image.dateDAjout != null && image.dateDAjout.isValid() ? image.dateDAjout.toJSON() : null,
            dateValidation: image.dateValidation != null && image.dateValidation.isValid() ? image.dateValidation.toJSON() : null
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
            res.body.forEach((image: IImage) => {
                image.dateDAjout = image.dateDAjout != null ? moment(image.dateDAjout) : null;
                image.dateValidation = image.dateValidation != null ? moment(image.dateValidation) : null;
            });
        }
        return res;
    }
}
