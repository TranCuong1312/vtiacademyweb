export class NewsRequest{
    title: string;
    content: string;
    author: string;
    createDate: Date;
    image: string;
    shortDes: string;
  
    constructor(title,content,author,createDate,image,shortDes){
      this.title = title;
      this.content = content;
      this.author = author;
      this.createDate = createDate;
      this.image = image;
      this.shortDes = shortDes;
    }
}