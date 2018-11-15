import { IQuarto } from 'app/shared/model//quarto.model';

export interface IHotel {
    id?: number;
    nome?: string;
    nota?: number;
    telefone?: string;
    cidade?: string;
    estado?: string;
    endereco?: string;
    imagem?: string;
    quartos?: IQuarto[];
}

export class Hotel implements IHotel {
    constructor(
        public id?: number,
        public nome?: string,
        public nota?: number,
        public telefone?: string,
        public cidade?: string,
        public estado?: string,
        public endereco?: string,
        public imagem?: string,
        public quartos?: IQuarto[]
    ) {}
}
