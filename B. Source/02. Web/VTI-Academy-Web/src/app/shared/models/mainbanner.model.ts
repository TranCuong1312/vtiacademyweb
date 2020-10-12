import { Course } from './course.model';

export class MainBanner {
    id: number;
    url: string;
    name: string;
    image: string;

    constructor(name: string, url : string, image : string){
        this.name = name;
        this.url = url;
        this.image = image;
    }
}