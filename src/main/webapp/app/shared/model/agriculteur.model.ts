import { IImageAttaque } from 'app/shared/model/image-attaque.model';

export interface IAgriculteur {
    id?: number;
    localistion?: string;
    imageSends?: IImageAttaque[];
}

export class Agriculteur implements IAgriculteur {
    constructor(public id?: number, public localistion?: string, public imageSends?: IImageAttaque[]) {}
}
