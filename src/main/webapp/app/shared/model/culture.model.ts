import { IAttaque } from 'app/shared/model/attaque.model';
import { IMaladie } from 'app/shared/model/maladie.model';
import { IInsecteUtile } from 'app/shared/model/insecte-utile.model';
import { IInsecteRavageur } from 'app/shared/model/insecte-ravageur.model';
import { IHerbe } from 'app/shared/model/herbe.model';

export interface ICulture {
    id?: number;
    nomCulture?: string;
    imageCulture?: string;
    paysCulture?: string;
    attaques?: IAttaque[];
    cultureMaladies?: IMaladie[];
    cultureInsecteUtiles?: IInsecteUtile[];
    culturesRavageurs?: IInsecteRavageur[];
    cultureHerbes?: IHerbe[];
}

export class Culture implements ICulture {
    constructor(
        public id?: number,
        public nomCulture?: string,
        public imageCulture?: string,
        public paysCulture?: string,
        public attaques?: IAttaque[],
        public cultureMaladies?: IMaladie[],
        public cultureInsecteUtiles?: IInsecteUtile[],
        public culturesRavageurs?: IInsecteRavageur[],
        public cultureHerbes?: IHerbe[]
    ) {}
}
