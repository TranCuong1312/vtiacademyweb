import { Course } from './course.model';

export class SubCourse{
  id:number;
  name:string;
  createDate: String;
  content:string;
  note:string;
  active:boolean;

  constructor(name: string, createDate: string, content: string){
    this.name = name;
    this.createDate = createDate;
    this.content = content;
  }
}

