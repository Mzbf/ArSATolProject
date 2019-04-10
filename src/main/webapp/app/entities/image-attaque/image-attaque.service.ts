import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IImageAttaque } from 'app/shared/model/image-attaque.model';

type EntityResponseType = HttpResponse<IImageAttaque>;
type EntityArrayResponseType = HttpResponse<IImageAttaque[]>;

@Injectable({ providedIn: 'root' })
export class ImageAttaqueService {
    public resourceUrl = SERVER_API_URL + 'api/image-attaques';

    constructor(protected http: HttpClient) {}

    create(imageAttaque: IImageAttaque): Observable<EntityResponseType> {
        return this.http.post<IImageAttaque>(this.resourceUrl, imageAttaque, { observe: 'response' });
    }

    update(imageAttaque: IImageAttaque): Observable<EntityResponseType> {
        return this.http.put<IImageAttaque>(this.resourceUrl, imageAttaque, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IImageAttaque>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IImageAttaque[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
    saveImage(formData: FormData): Observable<any> {
        return this.http.post(SERVER_API_URL + 'api/imageAttaque', formData);
    }
}
