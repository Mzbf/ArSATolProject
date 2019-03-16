export interface IMethodeLutte {
    id?: number;
    methodeCulturale?: any;
    traitement?: any;
}

export class MethodeLutte implements IMethodeLutte {
    constructor(public id?: number, public methodeCulturale?: any, public traitement?: any) {}
}
