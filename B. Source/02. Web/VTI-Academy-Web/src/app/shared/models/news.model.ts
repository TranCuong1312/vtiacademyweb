export class News{
  id: number;
  title: string;
  content: string;
  author: string;
  createDate: Date;
  image: string;
  shortDes: string;

  constructor(params){
    this.id = params.id;
    this.title = params.title;
    this.content = params.content;
    this.author = params.author;
    this.createDate = params.createDate;
    this.image = params.image;
    this.shortDes = params.shortDes;
  }
  
}
