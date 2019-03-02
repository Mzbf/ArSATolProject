export interface IFamille {
    id?: number;
}

export class Famille implements IFamille {
    constructor(public id?: number) {}
}
