import { IVoo } from 'app/shared/model//voo.model';
import { ICompra } from 'app/shared/model//compra.model';

export interface IPassagem {
    id?: number;
    assento?: string;
    voo?: IVoo;
    compra?: ICompra;
}

export class Passagem implements IPassagem {
    constructor(public id?: number, public assento?: string, public voo?: IVoo, public compra?: ICompra) {}
}
