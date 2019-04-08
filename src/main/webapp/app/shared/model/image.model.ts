export interface IImage {
    id?: number;
    imageUrl?: string;
}

export class Image implements IImage {
    constructor(public id?: number, public imageUrl?: string) {}
}
