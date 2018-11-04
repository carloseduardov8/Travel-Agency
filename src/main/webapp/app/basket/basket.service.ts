import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAeroporto } from 'app/shared/model/aeroporto.model';
import { Compra } from 'app/shared/model/compra.model';

type EntityResponseType = HttpResponse<IAeroporto>;
type EntityArrayResponseType = HttpResponse<IAeroporto[]>;

@Injectable({ providedIn: 'root' })
export class BasketService {
    compra = new Compra();
}
