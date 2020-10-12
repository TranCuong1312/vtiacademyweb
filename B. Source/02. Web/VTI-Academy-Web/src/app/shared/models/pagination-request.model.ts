export class PaginationRequest{
    currentPage : number;
    size : number;
    totalPage : number;
    total : number; 

    constructor(params){
        this.currentPage = params.currentPage;
        this.size = params.size ;
        this.totalPage = params.totalPage;
        this.total= params.total;
    }

}