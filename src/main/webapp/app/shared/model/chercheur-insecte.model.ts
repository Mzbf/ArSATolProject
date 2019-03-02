import { Moment } from 'moment';

export interface IChercheurInsecte {
    id?: number;
    dateDAjout?: Moment;
    dateValidation?: Moment;
    flag?: boolean;
}

export class ChercheurInsecte implements IChercheurInsecte {
    constructor(public id?: number, public dateDAjout?: Moment, public dateValidation?: Moment, public flag?: boolean) {
        this.flag = this.flag || false;
    }
}
