import { Moment } from 'moment';

export interface IImageEnvoye {
    id?: number;
    urlImage?: string;
    dateDAjout?: Moment;
    dateValidation?: Moment;
    flag?: boolean;
    agriculteurId?: number;
}

export class ImageEnvoye implements IImageEnvoye {
    constructor(
        public id?: number,
        public urlImage?: string,
        public dateDAjout?: Moment,
        public dateValidation?: Moment,
        public flag?: boolean,
        public agriculteurId?: number
    ) {
        this.flag = this.flag || false;
    }
}
