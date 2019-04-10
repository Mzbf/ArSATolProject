import { IInsecte } from 'app/shared/model/insecte.model';

export interface ITypeInsecte {
    id?: number;
    nomTypeInsecte?: string;
    description?: string;
    typeInsectes?: IInsecte[];
}

export class TypeInsecte implements ITypeInsecte {
    constructor(public id?: number, public nomTypeInsecte?: string, public description?: string, public typeInsectes?: IInsecte[]) {}
}
