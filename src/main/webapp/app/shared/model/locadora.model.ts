import { IVeiculo } from 'app/shared/model//veiculo.model';

export interface ILocadora {
    id?: number;
    nome?: string;
    logradouro?: string;
    numero?: number;
    complemento?: string;
    cep?: number;
    telefone?: number;
    veiculos?: IVeiculo[];
}

export class Locadora implements ILocadora {
    constructor(
        public id?: number,
        public nome?: string,
        public logradouro?: string,
        public numero?: number,
        public complemento?: string,
        public cep?: number,
        public telefone?: number,
        public veiculos?: IVeiculo[]
    ) {}
}
