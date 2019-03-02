import { Moment } from 'moment';
import { IInsecte } from 'app/shared/model/insecte.model';
import { IImage } from 'app/shared/model/image.model';
import { ICulture } from 'app/shared/model/culture.model';
import { IChercheur } from 'app/shared/model/chercheur.model';
import { IAdministrateur } from 'app/shared/model/administrateur.model';

export interface IAttaque {
    id?: number;
    localisation?: string;
    description?: string;
    flag?: boolean;
    dateValidation?: Moment;
    dateAjout?: Moment;
    insecte?: IInsecte;
    imageAttaques?: IImage[];
    culture?: ICulture;
    chercheur?: IChercheur;
    administrateur?: IAdministrateur;
}

export class Attaque implements IAttaque {
    constructor(
        public id?: number,
        public localisation?: string,
        public description?: string,
        public flag?: boolean,
        public dateValidation?: Moment,
        public dateAjout?: Moment,
        public insecte?: IInsecte,
        public imageAttaques?: IImage[],
        public culture?: ICulture,
        public chercheur?: IChercheur,
        public administrateur?: IAdministrateur
    ) {
        this.flag = this.flag || false;
    }
}
