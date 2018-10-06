import { IContrato } from 'app/shared/model//contrato.model';

export interface ISeguradora {
    id?: number;
    nome?: string;
    logradouro?: string;
    numero?: number;
    complemento?: string;
    cep?: number;
    telefone?: number;
    contratoes?: IContrato[];
}

export class Seguradora implements ISeguradora {
    constructor(
        public id?: number,
        public nome?: string,
        public logradouro?: string,
        public numero?: number,
        public complemento?: string,
        public cep?: number,
        public telefone?: number,
        public contratoes?: IContrato[]
    ) {}
}
