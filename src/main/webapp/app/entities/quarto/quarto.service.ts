import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IQuarto } from 'app/shared/model/quarto.model';
import { ISeguro } from 'app/shared/model/seguro.model';

type EntityResponseType = HttpResponse<IQuarto>;
type EntityArrayResponseType = HttpResponse<IQuarto[]>;

@Injectable({ providedIn: 'root' })
export class QuartoService {
    private resourceUrl = SERVER_API_URL + 'api/quartos';

    constructor(private http: HttpClient) {}

    create(quarto: IQuarto): Observable<EntityResponseType> {
        return this.http.post<IQuarto>(this.resourceUrl, quarto, { observe: 'response' });
    }

    update(quarto: IQuarto): Observable<EntityResponseType> {
        return this.http.put<IQuarto>(this.resourceUrl, quarto, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IQuarto>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    findQuartos(cidade: String): Observable<EntityArrayResponseType> {
        return this.http.get<IQuarto[]>(`${this.resourceUrl}/cidade/${cidade}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IQuarto[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
