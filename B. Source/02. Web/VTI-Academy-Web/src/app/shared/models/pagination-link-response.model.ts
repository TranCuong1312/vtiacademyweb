export class LinkResponse{
    first : string;
    last : string;
    next : string;
    prev : string;

    constructor(params){
        this.first = params.first;
        this.last = params.last;
        this.next = params.next;
        this.prev = params.prev;
    }
}