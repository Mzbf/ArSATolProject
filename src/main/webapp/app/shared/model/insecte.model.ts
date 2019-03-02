import { Moment } from 'moment';
import { IMethodeLutte } from 'app/shared/model/methode-lutte.model';
import { IAttaque } from 'app/shared/model/attaque.model';
import { IImage } from 'app/shared/model/image.model';
import { IChercheur } from 'app/shared/model/chercheur.model';
import { IAdministrateur } from 'app/shared/model/administrateur.model';

export interface IInsecte {
    id?: number;
    nomInsecte?: string;
    nomScienInsecte?: string;
    cycleBio?: string;
    flag?: boolean;
    dateValidation?: Moment;
    dateAjout?: Moment;
    methode?: IMethodeLutte;
    attaques?: IAttaque[];
    imageInsectes?: IImage[];
    chercheur?: IChercheur;
    administrateur?: IAdministrateur;
}

export class Insecte implements IInsecte {
    constructor(
        public id?: number,
        public nomInsecte?: string,
        public nomScienInsecte?: string,
        public cycleBio?: string,
        public flag?: boolean,
        public dateValidation?: Moment,
        public dateAjout?: Moment,
        public methode?: IMethodeLutte,
        public attaques?: IAttaque[],
        public imageInsectes?: IImage[],
        public chercheur?: IChercheur,
        public administrateur?: IAdministrateur
    ) {
        this.flag = this.flag || false;
    }
}
