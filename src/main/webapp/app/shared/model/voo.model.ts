import { IPassagem } from 'app/shared/model//passagem.model';
import { ILinhaAerea } from 'app/shared/model//linha-aerea.model';

export interface IVoo {
    id?: number;
    numero?: number;
    partida?: string;
    chegada?: string;
    origem?: string;
    destino?: string;
    passagems?: IPassagem[];
    linhaAerea?: ILinhaAerea;
}

export class Voo implements IVoo {
    constructor(
        public id?: number,
        public numero?: number,
        public partida?: string,
        public chegada?: string,
        public origem?: string,
        public destino?: string,
        public passagems?: IPassagem[],
        public linhaAerea?: ILinhaAerea
    ) {}
}
