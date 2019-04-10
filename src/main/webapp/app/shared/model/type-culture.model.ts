import { ICulture } from 'app/shared/model/culture.model';

export interface ITypeCulture {
    id?: number;
    nomTypeCulture?: string;
    description?: string;
    typeCultures?: ICulture[];
}

export class TypeCulture implements ITypeCulture {
    constructor(public id?: number, public nomTypeCulture?: string, public description?: string, public typeCultures?: ICulture[]) {}
}
