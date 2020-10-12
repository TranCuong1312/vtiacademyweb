import { CourseRoutingModule } from './courses-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoursesComponent } from './courses.component';
import { CourseOutcomeEditComponent } from './course-outcome-edit/course-outcome-edit.component';
import { SubcourseEditComponent } from './subcourse-edit/subcourse-edit.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import { AngularEditorModule } from '@kolkov/angular-editor';

@NgModule({
  declarations: [CoursesComponent, CourseOutcomeEditComponent, SubcourseEditComponent],
  imports: [
    CommonModule,
    CourseRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    AngularEditorModule
  ]
})
export class CoursesModule { }
