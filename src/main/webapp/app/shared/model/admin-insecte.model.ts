import { Moment } from 'moment';
import { IInsecte } from 'app/shared/model/insecte.model';
import { IAdministrateur } from 'app/shared/model/administrateur.model';

export interface IAdminInsecte {
    id?: number;
    dateDAjout?: Moment;
    dateValidation?: Moment;
    flag?: boolean;
    insecte?: IInsecte;
    administrateur?: IAdministrateur;
}

export class AdminInsecte implements IAdminInsecte {
    constructor(
        public id?: number,
        public dateDAjout?: Moment,
        public dateValidation?: Moment,
        public flag?: boolean,
        public insecte?: IInsecte,
        public administrateur?: IAdministrateur
    ) {
        this.flag = this.flag || false;
    }
}
