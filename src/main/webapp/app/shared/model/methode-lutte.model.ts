export interface IMethodeLutte {
    id?: number;
    methodeCulturale?: string;
    traitement?: string;
}

export class MethodeLutte implements IMethodeLutte {
    constructor(public id?: number, public methodeCulturale?: string, public traitement?: string) {}
}
