import { IVeiculo } from 'app/shared/model//veiculo.model';

export interface ILocadora {
    id?: number;
    nome?: string;
    telefone?: string;
    cidade?: string;
    estado?: string;
    endereco?: string;
    veiculos?: IVeiculo[];
}

export class Locadora implements ILocadora {
    constructor(
        public id?: number,
        public nome?: string,
        public telefone?: string,
        public cidade?: string,
        public estado?: string,
        public endereco?: string,
        public veiculos?: IVeiculo[]
    ) {}
}
