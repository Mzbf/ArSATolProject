import { IImageCulture } from 'app/shared/model/image-culture.model';
import { IAttaque } from 'app/shared/model/attaque.model';
import { IMaladie } from 'app/shared/model/maladie.model';
import { IHerbe } from 'app/shared/model/herbe.model';
import { IZoneGeo } from 'app/shared/model/zone-geo.model';

export interface ICulture {
    id?: number;
    nomCulture?: string;
    imageCulture?: string;
    typeCultureId?: number;
    imagecultures?: IImageCulture[];
    listattaques?: IAttaque[];
    maladies?: IMaladie[];
    herbes?: IHerbe[];
    zones?: IZoneGeo[];
}

export class Culture implements ICulture {
    constructor(
        public id?: number,
        public nomCulture?: string,
        public imageCulture?: string,
        public typeCultureId?: number,
        public imagecultures?: IImageCulture[],
        public listattaques?: IAttaque[],
        public maladies?: IMaladie[],
        public herbes?: IHerbe[],
        public zones?: IZoneGeo[]
    ) {}
}
