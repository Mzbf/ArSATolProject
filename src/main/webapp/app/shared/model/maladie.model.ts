import { IImageMaladie } from 'app/shared/model/image-maladie.model';
import { ICulture } from 'app/shared/model/culture.model';

export interface IMaladie {
    id?: number;
    nomMaladie?: string;
    description?: any;
    imagesMaladie?: string;
    maladieMLId?: number;
    maladies?: IImageMaladie[];
    cultures?: ICulture[];
}

export class Maladie implements IMaladie {
    constructor(
        public id?: number,
        public nomMaladie?: string,
        public description?: any,
        public imagesMaladie?: string,
        public maladieMLId?: number,
        public maladies?: IImageMaladie[],
        public cultures?: ICulture[]
    ) {}
}
