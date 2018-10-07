export interface ICidade {
    id?: number;
}

export class Cidade implements ICidade {
    constructor(public id?: number) {}
}
