export class Mentor{
    id: number;
    name: string;
    position: string;
    content: string;
    img: string;

    constructor(params){
        this.id = params.id;
        this.name = params.name;
        this.position = params.position;
        this.content = params.content;
        this.img = params.img;
    }
}
