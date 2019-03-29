import { IFamille } from 'app/shared/model/famille.model';

export interface IOrdre {
    id?: number;
    nomOrdre?: string;
    description?: any;
    imageOrdre?: string;
    ordres?: IFamille[];
}

export class Ordre implements IOrdre {
    constructor(
        public id?: number,
        public nomOrdre?: string,
        public description?: any,
        public imageOrdre?: string,
        public ordres?: IFamille[]
    ) {}
}
