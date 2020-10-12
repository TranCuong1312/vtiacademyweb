import { LinkResponse } from './pagination-link-response.model';
import { PaginationRequest } from './pagination-request.model';

export class PaginationResponse<T>{
    contents : T;
    link : LinkResponse;
    metadata : PaginationRequest;

    constructor(params){
        this.contents = params.contents;
        this.link = params.link;
        this.metadata = params.metadata;
    }
}
