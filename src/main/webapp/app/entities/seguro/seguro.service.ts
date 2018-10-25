import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISeguro } from 'app/shared/model/seguro.model';

type EntityResponseType = HttpResponse<ISeguro>;
type EntityArrayResponseType = HttpResponse<ISeguro[]>;

@Injectable({ providedIn: 'root' })
export class SeguroService {
    private resourceUrl = SERVER_API_URL + 'api/seguros';

    constructor(private http: HttpClient) {}

    create(seguro: ISeguro): Observable<EntityResponseType> {
        return this.http.post<ISeguro>(this.resourceUrl, seguro, { observe: 'response' });
    }

    update(seguro: ISeguro): Observable<EntityResponseType> {
        return this.http.put<ISeguro>(this.resourceUrl, seguro, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISeguro>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISeguro[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
