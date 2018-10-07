export interface ITelefone {
    id?: number;
}

export class Telefone implements ITelefone {
    constructor(public id?: number) {}
}
