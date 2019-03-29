export interface IMethodeLutte {
    id?: number;
    type?: string;
    methodeCulturale?: any;
    traitement?: any;
    imageML?: string;
}

export class MethodeLutte implements IMethodeLutte {
    constructor(
        public id?: number,
        public type?: string,
        public methodeCulturale?: any,
        public traitement?: any,
        public imageML?: string
    ) {}
}
