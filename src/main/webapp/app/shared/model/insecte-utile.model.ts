import { ICulture } from 'app/shared/model/culture.model';

export interface IInsecteUtile {
    id?: number;
    typeUtilId?: number;
    cultures?: ICulture[];
    chercheurId?: number;
    familleId?: number;
}

export class InsecteUtile implements IInsecteUtile {
    constructor(
        public id?: number,
        public typeUtilId?: number,
        public cultures?: ICulture[],
        public chercheurId?: number,
        public familleId?: number
    ) {}
}
