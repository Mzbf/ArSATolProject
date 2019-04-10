import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IImageCulture } from 'app/shared/model/image-culture.model';

type EntityResponseType = HttpResponse<IImageCulture>;
type EntityArrayResponseType = HttpResponse<IImageCulture[]>;

@Injectable({ providedIn: 'root' })
export class ImageCultureService {
    public resourceUrl = SERVER_API_URL + 'api/image-cultures';

    constructor(protected http: HttpClient) {}

    create(imageCulture: IImageCulture): Observable<EntityResponseType> {
        return this.http.post<IImageCulture>(this.resourceUrl, imageCulture, { observe: 'response' });
    }

    update(imageCulture: IImageCulture): Observable<EntityResponseType> {
        return this.http.put<IImageCulture>(this.resourceUrl, imageCulture, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IImageCulture>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IImageCulture[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
    saveImage(formData: FormData): Observable<any> {
        return this.http.post(SERVER_API_URL + 'api/imageCulture', formData);
    }
}
