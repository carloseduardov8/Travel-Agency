import { IVeiculo } from 'app/shared/model//veiculo.model';
import { ICompra } from 'app/shared/model//compra.model';

export interface ILocacao {
    id?: number;
    dataInicio?: string;
    dataFim?: string;
    valor?: number;
    veiculo?: IVeiculo;
    compra?: ICompra;
}

export class Locacao implements ILocacao {
    constructor(
        public id?: number,
        public dataInicio?: string,
        public dataFim?: string,
        public valor?: number,
        public veiculo?: IVeiculo,
        public compra?: ICompra
    ) {}
}
