import { Moment } from 'moment';
import { IMethodeLutte } from 'app/shared/model/methode-lutte.model';
import { IAttaque } from 'app/shared/model/attaque.model';
import { IImageAttaque } from 'app/shared/model/image-attaque.model';
import { IChercheur } from 'app/shared/model/chercheur.model';
import { IAdministrateur } from 'app/shared/model/administrateur.model';

export interface IInsecte {
    id?: number;
    nomInsecte?: string;
    nomScienInsecte?: string;
    insecteImageContentType?: string;
    insecteImage?: any;
    description?: any;
    cycleBio?: any;
    autrePlante?: any;
    imageCycleContentType?: string;
    imageCycle?: any;
    dateValidation?: Moment;
    dateAjout?: Moment;
    methode?: IMethodeLutte;
    attaques?: IAttaque[];
    imageInsectes?: IImageAttaque[];
    chercheur?: IChercheur;
    administrateur?: IAdministrateur;
}

export class Insecte implements IInsecte {
    constructor(
        public id?: number,
        public nomInsecte?: string,
        public nomScienInsecte?: string,
        public insecteImageContentType?: string,
        public insecteImage?: any,
        public description?: any,
        public cycleBio?: any,
        public autrePlante?: any,
        public imageCycleContentType?: string,
        public imageCycle?: any,
        public dateValidation?: Moment,
        public dateAjout?: Moment,
        public methode?: IMethodeLutte,
        public attaques?: IAttaque[],
        public imageInsectes?: IImageAttaque[],
        public chercheur?: IChercheur,
        public administrateur?: IAdministrateur
    ) {}
}
