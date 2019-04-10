export interface IImageHerbe {
    id?: number;
    imageUrl?: string;
    herbeId?: number;
}

export class ImageHerbe implements IImageHerbe {
    constructor(public id?: number, public imageUrl?: string, public herbeId?: number) {}
}
