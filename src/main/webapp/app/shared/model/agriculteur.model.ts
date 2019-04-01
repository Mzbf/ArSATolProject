import { IImageEnvoye } from 'app/shared/model/image-envoye.model';

export interface IAgriculteur {
    id?: number;
    specialite?: string;
    paysId?: number;
    userId?: number;
    agriculteurs?: IImageEnvoye[];
}

export class Agriculteur implements IAgriculteur {
    constructor(
        public id?: number,
        public specialite?: string,
        public paysId?: number,
        public userId?: number,
        public agriculteurs?: IImageEnvoye[]
    ) {}
}
