import { IImageEnvoye } from 'app/shared/model/image-envoye.model';

export interface IAgriculteur {
    id?: number;
    pays?: string;
    userId?: number;
    agriculteurs?: IImageEnvoye[];
}

export class Agriculteur implements IAgriculteur {
    constructor(public id?: number, public pays?: string, public userId?: number, public agriculteurs?: IImageEnvoye[]) {}
}
