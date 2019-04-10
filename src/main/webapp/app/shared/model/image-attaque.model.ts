export interface IImageAttaque {
    id?: number;
    imageUrl?: string;
    attaqueId?: number;
}

export class ImageAttaque implements IImageAttaque {
    constructor(public id?: number, public imageUrl?: string, public attaqueId?: number) {}
}
