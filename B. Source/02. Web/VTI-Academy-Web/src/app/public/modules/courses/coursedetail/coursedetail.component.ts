import { Component, OnInit } from '@angular/core';
import { CourseOutcome } from 'src/app/shared/models/course-outcome.model';
import { SubCourse } from 'src/app/shared/models/subcourse.model';
import { Course } from 'src/app/shared/models/course.model';
import { CourseService } from 'src/app/public/common/services/course.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-coursedetail',
  templateUrl: './coursedetail.component.html',
  styleUrls: ['./coursedetail.component.scss']
})
export class CoursedetailComponent implements OnInit {
  subcourse: string = '';
  course: Course;
  courseOutcomes: CourseOutcome[];
  subCourses: SubCourse[];
  id: number;
  constructor(private route: ActivatedRoute,
    private courseService: CourseService) { }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params['id'];
    });
    this.getCourseDetail1();
    if (!localStorage.getItem('foo')) { 
      localStorage.setItem('foo', 'no reload') 
      location.reload() 
      window.scroll(0,0);
    } else {
      localStorage.removeItem('foo') 
    }
  }
  onClick(index: number) {
    this.subcourse = this.subCourses[index].content;
  }
  async getCourseDetail1(): Promise<void> {
    let result = await this.courseService.getCourseDetail(null, this.id);
    this.course = result.data;
    //this.courseOutcomes = this.course?.courseOutcome;
    this.subCourses = this.course?.subCourses;
  }
}
