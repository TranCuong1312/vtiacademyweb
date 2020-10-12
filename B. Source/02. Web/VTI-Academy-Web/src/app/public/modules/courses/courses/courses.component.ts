import { Component, OnInit } from '@angular/core';
import { CourseService } from 'src/app/public/common/services/course.service';

@Component({
    selector: 'app-courses',
    templateUrl: './courses.component.html',
    styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

    courses: any[] = [];

    constructor(private courseService: CourseService) { }

    ngOnInit(): void {
      this.getCourseList();
      if (!localStorage.getItem('foo')) { 
        localStorage.setItem('foo', 'no reload') 
        location.reload() 
        window.scroll(0,0);
      } else {
        localStorage.removeItem('foo') 
      }
    }

    async getCourseList():Promise<void> {
      let result = await this.courseService.getListCourses(null);
      this.courses = result.data;
    }

}
