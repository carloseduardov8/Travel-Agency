import { ILocacao } from 'app/shared/model//locacao.model';
import { ILocadora } from 'app/shared/model//locadora.model';

export interface IVeiculo {
    id?: number;
    tipo?: string;
    fabricante?: string;
    modelo?: string;
    cor?: string;
    numPassageiros?: number;
    imagem?: string;
    valor?: number;
    locacaos?: ILocacao[];
    locadora?: ILocadora;
}

export class Veiculo implements IVeiculo {
    constructor(
        public id?: number,
        public tipo?: string,
        public fabricante?: string,
        public modelo?: string,
        public cor?: string,
        public numPassageiros?: number,
        public imagem?: string,
        public valor?: number,
        public locacaos?: ILocacao[],
        public locadora?: ILocadora
    ) {}
}
