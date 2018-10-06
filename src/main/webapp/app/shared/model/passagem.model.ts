import { IVoo } from 'app/shared/model//voo.model';
import { ICliente } from 'app/shared/model//cliente.model';

export interface IPassagem {
    id?: number;
    assento?: string;
    valor?: number;
    nome?: string;
    cpf?: number;
    voo?: IVoo;
    cliente?: ICliente;
}

export class Passagem implements IPassagem {
    constructor(
        public id?: number,
        public assento?: string,
        public valor?: number,
        public nome?: string,
        public cpf?: number,
        public voo?: IVoo,
        public cliente?: ICliente
    ) {}
}
