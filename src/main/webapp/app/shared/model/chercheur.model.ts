import { IInsecteRavageur } from 'app/shared/model/insecte-ravageur.model';
import { IInsecteUtile } from 'app/shared/model/insecte-utile.model';
import { IAttaque } from 'app/shared/model/attaque.model';

export interface IChercheur {
    id?: number;
    institut?: string;
    pays?: string;
    specialite?: string;
    userId?: number;
    ajoutRavageurs?: IInsecteRavageur[];
    ajoutInsUtiles?: IInsecteUtile[];
    ajoutAttaques?: IAttaque[];
}

export class Chercheur implements IChercheur {
    constructor(
        public id?: number,
        public institut?: string,
        public pays?: string,
        public specialite?: string,
        public userId?: number,
        public ajoutRavageurs?: IInsecteRavageur[],
        public ajoutInsUtiles?: IInsecteUtile[],
        public ajoutAttaques?: IAttaque[]
    ) {}
}
