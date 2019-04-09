export interface IFamille {
    id?: number;
    nomFamille?: string;
    description?: any;
    imageFamiile?: string;
    ordreId?: number;
}

export class Famille implements IFamille {
    constructor(
        public id?: number,
        public nomFamille?: string,
        public description?: any,
        public imageFamiile?: string,
        public ordreId?: number
    ) {}
}
