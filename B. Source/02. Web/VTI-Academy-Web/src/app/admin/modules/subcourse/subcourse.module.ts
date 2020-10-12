import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { SubcourseComponent } from './subcourse.component';
import {SubcourseRoutingModule} from './subcourse-routing';
import { HttpClientModule} from '@angular/common/http';
import { AngularEditorModule } from '@kolkov/angular-editor';

@NgModule({
  declarations: [SubcourseComponent],
  imports: [
    CommonModule,
    SubcourseRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    AngularEditorModule
  ],
  providers: [
    DatePipe
  ]
})
export class SubcourseModule { }
