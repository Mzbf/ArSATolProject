export interface IImageCulture {
    id?: number;
    imageUrl?: string;
    cultureId?: number;
}

export class ImageCulture implements IImageCulture {
    constructor(public id?: number, public imageUrl?: string, public cultureId?: number) {}
}
