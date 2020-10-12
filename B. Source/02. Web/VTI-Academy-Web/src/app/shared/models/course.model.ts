import { SubCourse } from './subcourse.model';
import { CourseOutcome } from './course-outcome.model';
export class Course{
  id: number;
  name: string;
  img: string;
  curriculum: string;
  intro: string;
  isActive: boolean;
 // courseOutcome: CourseOutcome[];
  note: string;
  subCourses: SubCourse[];
  createDate: Date;

  constructor(name: string, img: string, intro: string, curriculum: string){
    this.name = name;
    this.img = img;
    this.intro = intro;
    this.curriculum = curriculum;
  }

}