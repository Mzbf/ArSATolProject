import { IAttaque } from 'app/shared/model/attaque.model';

export interface IChercheur {
    id?: number;
    institut?: string;
    pays?: string;
    specialite?: string;
    userId?: number;
    ajoutAttaques?: IAttaque[];
}

export class Chercheur implements IChercheur {
    constructor(
        public id?: number,
        public institut?: string,
        public pays?: string,
        public specialite?: string,
        public userId?: number,
        public ajoutAttaques?: IAttaque[]
    ) {}
}
