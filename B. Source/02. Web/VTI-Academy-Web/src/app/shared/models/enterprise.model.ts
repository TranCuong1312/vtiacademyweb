export class Enterprise{
    id: number;
    name: string;
    content: string;
    icon: string;
    image: string;

    constructor(image, content, icon, name){
        this.image = image;
        this.content = content;
        this.icon = icon;
        this.name = name;
    }
}
