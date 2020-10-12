import { NewsRoutingModule } from './news-routing.module';
import { NewsComponent } from './news.component';
import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import { AngularEditorModule } from '@kolkov/angular-editor';

@NgModule({
  declarations: [NewsComponent],
  imports: [
    CommonModule,
    NewsRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    AngularEditorModule
  ],
  providers : [DatePipe]
})
export class NewsModule { }
