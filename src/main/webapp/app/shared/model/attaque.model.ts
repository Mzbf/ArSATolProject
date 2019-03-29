import { Moment } from 'moment';

export const enum Localisation {
    FEUILLES = 'FEUILLES',
    FRUITS = 'FRUITS',
    FLEURES = 'FLEURES',
    TIGE = 'TIGE',
    RACINE = 'RACINE'
}

export interface IAttaque {
    id?: number;
    description?: any;
    flag?: boolean;
    localisation?: Localisation;
    dateValidation?: Moment;
    dateAjout?: Moment;
    imagesAttaque?: string;
    insecteRavageurId?: number;
    cultureId?: number;
    chercheurId?: number;
    typeDegatId?: number;
}

export class Attaque implements IAttaque {
    constructor(
        public id?: number,
        public description?: any,
        public flag?: boolean,
        public localisation?: Localisation,
        public dateValidation?: Moment,
        public dateAjout?: Moment,
        public imagesAttaque?: string,
        public insecteRavageurId?: number,
        public cultureId?: number,
        public chercheurId?: number,
        public typeDegatId?: number
    ) {
        this.flag = this.flag || false;
    }
}
