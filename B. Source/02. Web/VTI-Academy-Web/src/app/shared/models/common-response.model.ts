export class BaseResponse<T> {
    type : string;
    code : number;
    message : string;
    data : T;
    constructor(param){
        this.type = param.type;
        this.code = param.code;
        this.message = param.message;
        this.data = param.data;
    }
}
