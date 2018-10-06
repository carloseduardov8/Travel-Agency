import { ICliente } from 'app/shared/model//cliente.model';
import { ISeguradora } from 'app/shared/model//seguradora.model';

export interface IContrato {
    id?: number;
    numero?: number;
    valor?: number;
    descricao?: string;
    cliente?: ICliente;
    seguradora?: ISeguradora;
}

export class Contrato implements IContrato {
    constructor(
        public id?: number,
        public numero?: number,
        public valor?: number,
        public descricao?: string,
        public cliente?: ICliente,
        public seguradora?: ISeguradora
    ) {}
}
