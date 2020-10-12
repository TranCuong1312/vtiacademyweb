export class MentorRequest{
    name: string;
    position: string;
    content: string;
    img: string;
  
    constructor(name, position, content, img){
      this.name = name;
      this.position = position;
      this.content = content;
      this.img = img;
    }
}