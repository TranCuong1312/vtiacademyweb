import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewsRoutingModule } from './news-routing.module';
import { ListNewsComponent } from './list-news/list-news.component';
import { NewDetailComponent } from './new-detail/new-detail.component';

@NgModule({
  declarations: [ListNewsComponent, NewDetailComponent],
  imports: [
    CommonModule,
    NewsRoutingModule
  ],
  exports : [ListNewsComponent,NewDetailComponent]

})
export class NewsModule { }
