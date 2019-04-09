import { Moment } from 'moment';
import { IImageInsecte } from 'app/shared/model/image-insecte.model';
import { IAttaque } from 'app/shared/model/attaque.model';

export interface IInsecte {
    id?: number;
    nomInsecte?: string;
    nomScienInsecte?: string;
    insecteImage?: string;
    description?: any;
    cycleBio?: any;
    autrePlante?: any;
    imageCycle?: string;
    dateValidation?: Moment;
    dateAjout?: Moment;
    typeInsecteId?: number;
    insectes?: IImageInsecte[];
    listattaques?: IAttaque[];
}

export class Insecte implements IInsecte {
    constructor(
        public id?: number,
        public nomInsecte?: string,
        public nomScienInsecte?: string,
        public insecteImage?: string,
        public description?: any,
        public cycleBio?: any,
        public autrePlante?: any,
        public imageCycle?: string,
        public dateValidation?: Moment,
        public dateAjout?: Moment,
        public typeInsecteId?: number,
        public insectes?: IImageInsecte[],
        public listattaques?: IAttaque[]
    ) {}
}
