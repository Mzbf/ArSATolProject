export interface IImageMaladie {
    id?: number;
    imageUrl?: string;
    maladieId?: number;
}

export class ImageMaladie implements IImageMaladie {
    constructor(public id?: number, public imageUrl?: string, public maladieId?: number) {}
}
