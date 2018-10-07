export interface IEstado {
    id?: number;
}

export class Estado implements IEstado {
    constructor(public id?: number) {}
}
