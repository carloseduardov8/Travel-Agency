import { IVoo } from 'app/shared/model//voo.model';

export interface ILinhaAerea {
    id?: number;
    nome?: string;
    codigo?: string;
    logradouro?: string;
    numero?: number;
    complemento?: string;
    cep?: number;
    telefone?: number;
    voos?: IVoo[];
}

export class LinhaAerea implements ILinhaAerea {
    constructor(
        public id?: number,
        public nome?: string,
        public codigo?: string,
        public logradouro?: string,
        public numero?: number,
        public complemento?: string,
        public cep?: number,
        public telefone?: number,
        public voos?: IVoo[]
    ) {}
}
