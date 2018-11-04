import { IVoo } from 'app/shared/model//voo.model';

export interface IAeroporto {
    id?: number;
    nome?: string;
    telefone?: string;
    cidade?: string;
    estado?: string;
    endereco?: string;
    vemDes?: IVoo[];
    vaiParas?: IVoo[];
}

export class Aeroporto implements IAeroporto {
    constructor(
        public id?: number,
        public nome?: string,
        public telefone?: string,
        public cidade?: string,
        public estado?: string,
        public endereco?: string,
        public vemDes?: IVoo[],
        public vaiParas?: IVoo[]
    ) {}
}
