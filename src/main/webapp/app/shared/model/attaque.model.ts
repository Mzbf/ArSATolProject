import { Moment } from 'moment';
import { IInsecte } from 'app/shared/model/insecte.model';
import { IImageAttaque } from 'app/shared/model/image-attaque.model';
import { ICulture } from 'app/shared/model/culture.model';
import { IChercheur } from 'app/shared/model/chercheur.model';
import { IAdministrateur } from 'app/shared/model/administrateur.model';

export const enum Localisation {
    FEUILLES = 'FEUILLES',
    FRUITS = 'FRUITS',
    FLEURES = 'FLEURES',
    TIGE = 'TIGE'
}

export const enum TypeDegat {
    TACHE = 'TACHE',
    GALERIE = 'GALERIE',
    DEFORMATION = 'DEFORMATION',
    NECROSE = 'NECROSE'
}

export interface IAttaque {
    id?: number;
    localisation?: Localisation;
    description?: any;
    flag?: boolean;
    typeDegat?: TypeDegat;
    dateValidation?: Moment;
    dateAjout?: Moment;
    insecte?: IInsecte;
    imageAttaques?: IImageAttaque[];
    culture?: ICulture;
    chercheur?: IChercheur;
    administrateur?: IAdministrateur;
}

export class Attaque implements IAttaque {
    constructor(
        public id?: number,
        public localisation?: Localisation,
        public description?: any,
        public flag?: boolean,
        public typeDegat?: TypeDegat,
        public dateValidation?: Moment,
        public dateAjout?: Moment,
        public insecte?: IInsecte,
        public imageAttaques?: IImageAttaque[],
        public culture?: ICulture,
        public chercheur?: IChercheur,
        public administrateur?: IAdministrateur
    ) {
        this.flag = this.flag || false;
    }
}
