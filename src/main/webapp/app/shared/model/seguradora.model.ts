import { ISeguro } from 'app/shared/model//seguro.model';

export interface ISeguradora {
    id?: number;
    nome?: string;
    telefone?: string;
    cidade?: string;
    estado?: string;
    endereco?: string;
    seguros?: ISeguro[];
}

export class Seguradora implements ISeguradora {
    constructor(
        public id?: number,
        public nome?: string,
        public telefone?: string,
        public cidade?: string,
        public estado?: string,
        public endereco?: string,
        public seguros?: ISeguro[]
    ) {}
}
