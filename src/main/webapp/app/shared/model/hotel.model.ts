import { IQuarto } from 'app/shared/model//quarto.model';

export interface IHotel {
    id?: number;
    nome?: string;
    nota?: number;
    logradouro?: string;
    numero?: number;
    complemento?: string;
    cep?: number;
    telefone?: number;
    quartos?: IQuarto[];
}

export class Hotel implements IHotel {
    constructor(
        public id?: number,
        public nome?: string,
        public nota?: number,
        public logradouro?: string,
        public numero?: number,
        public complemento?: string,
        public cep?: number,
        public telefone?: number,
        public quartos?: IQuarto[]
    ) {}
}
