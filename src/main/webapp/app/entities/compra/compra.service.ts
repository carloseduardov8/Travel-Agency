import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICompra } from 'app/shared/model/compra.model';
import { ISeguro } from 'app/shared/model/seguro.model';

type EntityResponseType = HttpResponse<ICompra>;
type EntityArrayResponseType = HttpResponse<ICompra[]>;

@Injectable({ providedIn: 'root' })
export class CompraService {
    private resourceUrl = SERVER_API_URL + 'api/compras';

    constructor(private http: HttpClient) {}

    create(compra: ICompra): Observable<EntityResponseType> {
        return this.http.post<ICompra>(this.resourceUrl, compra, { observe: 'response' });
    }

    update(compra: ICompra): Observable<EntityResponseType> {
        return this.http.put<ICompra>(this.resourceUrl, compra, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICompra>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    getComprasByUser(): Observable<EntityArrayResponseType> {
        return this.http.get<ISeguro[]>(`${this.resourceUrl}/comprasByUser`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICompra[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
