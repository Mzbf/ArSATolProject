import { IAttaque } from 'app/shared/model/attaque.model';
import { ICulture } from 'app/shared/model/culture.model';

export interface IInsecteRavageur {
    id?: number;
    typeRavId?: number;
    ravageurMLId?: number;
    ravageurs?: IAttaque[];
    cultures?: ICulture[];
    chercheurId?: number;
    familleId?: number;
}

export class InsecteRavageur implements IInsecteRavageur {
    constructor(
        public id?: number,
        public typeRavId?: number,
        public ravageurMLId?: number,
        public ravageurs?: IAttaque[],
        public cultures?: ICulture[],
        public chercheurId?: number,
        public familleId?: number
    ) {}
}
