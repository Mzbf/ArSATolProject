export interface IDemo {
    id?: number;
    imageDemo?: string;
    audio?: string;
    videoDemo?: string;
}

export class Demo implements IDemo {
    constructor(public id?: number, public imageDemo?: string, public audio?: string, public videoDemo?: string) {}
}
