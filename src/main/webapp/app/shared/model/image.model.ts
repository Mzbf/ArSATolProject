import { Moment } from 'moment';
import { IInsecte } from 'app/shared/model/insecte.model';
import { IAttaque } from 'app/shared/model/attaque.model';
import { IAgriculteur } from 'app/shared/model/agriculteur.model';

export interface IImage {
    id?: number;
    urlImage?: string;
    imageContentType?: string;
    image?: any;
    dateDAjout?: Moment;
    dateValidation?: Moment;
    flag?: boolean;
    insecte?: IInsecte;
    attaque?: IAttaque;
    agriculteur?: IAgriculteur;
}

export class Image implements IImage {
    constructor(
        public id?: number,
        public urlImage?: string,
        public imageContentType?: string,
        public image?: any,
        public dateDAjout?: Moment,
        public dateValidation?: Moment,
        public flag?: boolean,
        public insecte?: IInsecte,
        public attaque?: IAttaque,
        public agriculteur?: IAgriculteur
    ) {
        this.flag = this.flag || false;
    }
}
