import { IVoo } from 'app/shared/model//voo.model';

export interface ILinhaAerea {
    id?: number;
    nome?: string;
    telefone?: string;
    imagem?: string;
    voos?: IVoo[];
}

export class LinhaAerea implements ILinhaAerea {
    constructor(public id?: number, public nome?: string, public telefone?: string, public imagem?: string, public voos?: IVoo[]) {}
}
