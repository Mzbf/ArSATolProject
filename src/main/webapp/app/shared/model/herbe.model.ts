import { IImageHerbe } from 'app/shared/model/image-herbe.model';
import { ICulture } from 'app/shared/model/culture.model';

export interface IHerbe {
    id?: number;
    nomHerbe?: string;
    description?: any;
    imagesHerbe?: string;
    herbeMLId?: number;
    herbes?: IImageHerbe[];
    cultures?: ICulture[];
}

export class Herbe implements IHerbe {
    constructor(
        public id?: number,
        public nomHerbe?: string,
        public description?: any,
        public imagesHerbe?: string,
        public herbeMLId?: number,
        public herbes?: IImageHerbe[],
        public cultures?: ICulture[]
    ) {}
}
