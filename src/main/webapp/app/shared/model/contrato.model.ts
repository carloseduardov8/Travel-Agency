import { ICompra } from 'app/shared/model//compra.model';
import { ISeguro } from 'app/shared/model//seguro.model';

export interface IContrato {
    id?: number;
    numPessoas?: number;
    dataInicio?: string;
    dataFim?: string;
    valor?: number;
    compra?: ICompra;
    seguro?: ISeguro;
}

export class Contrato implements IContrato {
    constructor(
        public id?: number,
        public numPessoas?: number,
        public dataInicio?: string,
        public dataFim?: string,
        public valor?: number,
        public compra?: ICompra,
        public seguro?: ISeguro
    ) {}
}
