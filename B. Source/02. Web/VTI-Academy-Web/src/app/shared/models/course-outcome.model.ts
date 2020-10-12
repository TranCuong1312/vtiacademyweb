import { Course } from './course.model';

export interface CourseOutcome{
  id: number;
  name: string;
  course:Course;
}