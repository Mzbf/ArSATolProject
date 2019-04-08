export interface IImageInsecte {
    id?: number;
    imageUrl?: string;
    insecteId?: number;
}

export class ImageInsecte implements IImageInsecte {
    constructor(public id?: number, public imageUrl?: string, public insecteId?: number) {}
}
