import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

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
        return this.http.post<IImageInsecte>(this.resourceUrl, imageInsecte, { observe: 'response' });
    }

    update(imageInsecte: IImageInsecte): Observable<EntityResponseType> {
        return this.http.put<IImageInsecte>(this.resourceUrl, imageInsecte, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IImageInsecte>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IImageInsecte[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
    saveImage(formData: FormData): Observable<any> {
        return this.http.post(SERVER_API_URL + 'api/imageInsecte', formData);
    }
}
