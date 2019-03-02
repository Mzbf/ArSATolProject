export interface IRole {
    id?: number;
    nomRole?: string;
}

export class Role implements IRole {
    constructor(public id?: number, public nomRole?: string) {}
}
