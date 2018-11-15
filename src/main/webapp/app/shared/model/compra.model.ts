import { IPassagem } from 'app/shared/model//passagem.model';
import { ILocacao } from 'app/shared/model//locacao.model';
import { IReserva } from 'app/shared/model//reserva.model';
import { IContrato } from 'app/shared/model//contrato.model';
import { IUser } from 'app/core/user/user.model';

export interface ICompra {
    id?: number;
    user?: IUser;
}

export class Compra implements ICompra {
    constructor(public id?: number, public user?: IUser) {}
}
