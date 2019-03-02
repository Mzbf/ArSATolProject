import { IInsecte } from 'app/shared/model/insecte.model';
import { IAttaque } from 'app/shared/model/attaque.model';

export interface IChercheur {
    id?: number;
    institut?: string;
    ajoutInsectes?: IInsecte[];
    ajoutAttaques?: IAttaque[];
}

export class Chercheur implements IChercheur {
    constructor(public id?: number, public institut?: string, public ajoutInsectes?: IInsecte[], public ajoutAttaques?: IAttaque[]) {}
}
