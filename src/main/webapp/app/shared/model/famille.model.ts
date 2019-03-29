import { IInsecteRavageur } from 'app/shared/model/insecte-ravageur.model';
import { IInsecteUtile } from 'app/shared/model/insecte-utile.model';

export interface IFamille {
    id?: number;
    nomFamille?: string;
    description?: any;
    imageFamiile?: string;
    familleRavageurs?: IInsecteRavageur[];
    familleInseUtiles?: IInsecteUtile[];
    ordreId?: number;
}

export class Famille implements IFamille {
    constructor(
        public id?: number,
        public nomFamille?: string,
        public description?: any,
        public imageFamiile?: string,
        public familleRavageurs?: IInsecteRavageur[],
        public familleInseUtiles?: IInsecteUtile[],
        public ordreId?: number
    ) {}
}
