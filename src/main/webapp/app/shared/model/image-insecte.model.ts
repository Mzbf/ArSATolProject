import { Moment } from 'moment';
import { IImage } from 'app/shared/model/image.model';
import { IAgriculteur } from 'app/shared/model/agriculteur.model';

export interface IImageInsecte {
    id?: number;
    dateDAjout?: Moment;
    dateValidation?: Moment;
    flag?: boolean;
    image?: IImage;
    agriculteur?: IAgriculteur;
}

export class ImageInsecte implements IImageInsecte {
    constructor(
        public id?: number,
        public dateDAjout?: Moment,
        public dateValidation?: Moment,
        public flag?: boolean,
        public image?: IImage,
        public agriculteur?: IAgriculteur
    ) {
        this.flag = this.flag || false;
    }
}
