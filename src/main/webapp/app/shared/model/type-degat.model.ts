import { IAttaque } from 'app/shared/model/attaque.model';

export interface ITypeDegat {
    id?: number;
    typeDeg?: string;
    degats?: IAttaque[];
}

export class TypeDegat implements ITypeDegat {
    constructor(public id?: number, public typeDeg?: string, public degats?: IAttaque[]) {}
}
