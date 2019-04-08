import { IImageCulture } from 'app/shared/model/image-culture.model';
import { IAttaque } from 'app/shared/model/attaque.model';
import { IMaladie } from 'app/shared/model/maladie.model';
import { IInsecteUtile } from 'app/shared/model/insecte-utile.model';
import { IInsecteRavageur } from 'app/shared/model/insecte-ravageur.model';
import { IHerbe } from 'app/shared/model/herbe.model';
import { IZoneGeo } from 'app/shared/model/zone-geo.model';

export interface ICulture {
    id?: number;
    nomCulture?: string;
    imageCulture?: string;
    paysCulture?: string;
    typeCultureId?: number;
    cultures?: IImageCulture[];
    attaques?: IAttaque[];
    cultureMaladies?: IMaladie[];
    cultureInsecteUtiles?: IInsecteUtile[];
    culturesRavageurs?: IInsecteRavageur[];
    cultureHerbes?: IHerbe[];
    zones?: IZoneGeo[];
}

export class Culture implements ICulture {
    constructor(
        public id?: number,
        public nomCulture?: string,
        public imageCulture?: string,
        public paysCulture?: string,
        public typeCultureId?: number,
        public cultures?: IImageCulture[],
        public attaques?: IAttaque[],
        public cultureMaladies?: IMaladie[],
        public cultureInsecteUtiles?: IInsecteUtile[],
        public culturesRavageurs?: IInsecteRavageur[],
        public cultureHerbes?: IHerbe[],
        public zones?: IZoneGeo[]
    ) {}
}
