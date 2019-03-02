import { IImage } from 'app/shared/model/image.model';

export interface IAgriculteur {
    id?: number;
    localistion?: string;
    imageSends?: IImage[];
}

export class Agriculteur implements IAgriculteur {
    constructor(public id?: number, public localistion?: string, public imageSends?: IImage[]) {}
}
