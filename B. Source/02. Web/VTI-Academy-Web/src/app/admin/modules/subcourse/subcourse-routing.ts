import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SubcourseComponent } from './subcourse.component';

const routes: Routes = [
  {
    path:'',
    component: SubcourseComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SubcourseRoutingModule { }