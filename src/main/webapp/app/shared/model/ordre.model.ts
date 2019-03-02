export interface IOrdre {
    id?: number;
}

export class Ordre implements IOrdre {
    constructor(public id?: number) {}
}
