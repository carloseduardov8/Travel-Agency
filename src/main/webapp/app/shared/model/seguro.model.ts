import { IContrato } from 'app/shared/model//contrato.model';
import { ISeguradora } from 'app/shared/model//seguradora.model';

export interface ISeguro {
    id?: number;
    nome?: string;
    descricao?: string;
    valorPorPessoa?: number;
    contratoes?: IContrato[];
    seguradora?: ISeguradora;
}

export class Seguro implements ISeguro {
    constructor(
        public id?: number,
        public nome?: string,
        public descricao?: string,
        public valorPorPessoa?: number,
        public contratoes?: IContrato[],
        public seguradora?: ISeguradora
    ) {}
}
