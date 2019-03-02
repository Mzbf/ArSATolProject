export interface ISuperFamille {
    id?: number;
}

export class SuperFamille implements ISuperFamille {
    constructor(public id?: number) {}
}
