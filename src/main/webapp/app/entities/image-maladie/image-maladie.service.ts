import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IImageMaladie } from 'app/shared/model/image-maladie.model';

type EntityResponseType = HttpResponse<IImageMaladie>;
type EntityArrayResponseType = HttpResponse<IImageMaladie[]>;

@Injectable({ providedIn: 'root' })
export class ImageMaladieService {
    public resourceUrl = SERVER_API_URL + 'api/image-maladies';

    constructor(protected http: HttpClient) {}

    create(imageMaladie: IImageMaladie): Observable<EntityResponseType> {
        return this.http.post<IImageMaladie>(this.resourceUrl, imageMaladie, { observe: 'response' });
    }

    update(imageMaladie: IImageMaladie): Observable<EntityResponseType> {
        return this.http.put<IImageMaladie>(this.resourceUrl, imageMaladie, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IImageMaladie>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IImageMaladie[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
