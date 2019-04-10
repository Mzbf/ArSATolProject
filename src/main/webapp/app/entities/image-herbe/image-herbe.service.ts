import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IImageHerbe } from 'app/shared/model/image-herbe.model';

type EntityResponseType = HttpResponse<IImageHerbe>;
type EntityArrayResponseType = HttpResponse<IImageHerbe[]>;

@Injectable({ providedIn: 'root' })
export class ImageHerbeService {
    public resourceUrl = SERVER_API_URL + 'api/image-herbes';

    constructor(protected http: HttpClient) {}

    create(imageHerbe: IImageHerbe): Observable<EntityResponseType> {
        return this.http.post<IImageHerbe>(this.resourceUrl, imageHerbe, { observe: 'response' });
    }

    update(imageHerbe: IImageHerbe): Observable<EntityResponseType> {
        return this.http.put<IImageHerbe>(this.resourceUrl, imageHerbe, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IImageHerbe>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IImageHerbe[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
