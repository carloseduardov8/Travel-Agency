import { IQuarto } from 'app/shared/model//quarto.model';
import { ICompra } from 'app/shared/model//compra.model';

export interface IReserva {
    id?: number;
    numPessoas?: number;
    dataInicio?: string;
    dataFim?: string;
    valor?: number;
    quarto?: IQuarto;
    compra?: ICompra;
}

export class Reserva implements IReserva {
    constructor(
        public id?: number,
        public numPessoas?: number,
        public dataInicio?: string,
        public dataFim?: string,
        public valor?: number,
        public quarto?: IQuarto,
        public compra?: ICompra
    ) {}
}
