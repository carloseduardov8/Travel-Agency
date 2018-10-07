export interface IAeroporto {
    id?: number;
}

export class Aeroporto implements IAeroporto {
    constructor(public id?: number) {}
}
