export interface IEndereco {
    id?: number;
}

export class Endereco implements IEndereco {
    constructor(public id?: number) {}
}
