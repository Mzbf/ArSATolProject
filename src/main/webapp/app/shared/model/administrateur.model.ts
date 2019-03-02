import { IInsecte } from 'app/shared/model/insecte.model';
import { IAttaque } from 'app/shared/model/attaque.model';

export interface IAdministrateur {
    id?: number;
    adminAjoutInsectes?: IInsecte[];
    adminAjoutAttaques?: IAttaque[];
}

export class Administrateur implements IAdministrateur {
    constructor(public id?: number, public adminAjoutInsectes?: IInsecte[], public adminAjoutAttaques?: IAttaque[]) {}
}
