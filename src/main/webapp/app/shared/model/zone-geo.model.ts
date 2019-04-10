import { ICulture } from 'app/shared/model/culture.model';

export interface IZoneGeo {
    id?: number;
    pays?: string;
    region?: string;
    cultures?: ICulture[];
}

export class ZoneGeo implements IZoneGeo {
    constructor(public id?: number, public pays?: string, public region?: string, public cultures?: ICulture[]) {}
}
