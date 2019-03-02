import { IAttaque } from 'app/shared/model/attaque.model';

export interface ICulture {
    id?: number;
    nomCulture?: string;
    attaques?: IAttaque[];
}

export class Culture implements ICulture {
    constructor(public id?: number, public nomCulture?: string, public attaques?: IAttaque[]) {}
}
