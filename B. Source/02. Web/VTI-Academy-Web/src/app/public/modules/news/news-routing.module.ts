import { ListNewsComponent } from './list-news/list-news.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NewDetailComponent } from './new-detail/new-detail.component';

const routes: Routes = [
  { path: '', component: ListNewsComponent },
  { path: ':id', component: NewDetailComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class NewsRoutingModule {}
