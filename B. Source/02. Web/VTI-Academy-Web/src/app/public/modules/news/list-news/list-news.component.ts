import { News } from './../../../../shared/models/news.model';
import { Observable, of } from 'rxjs';
import { NewsService } from '../../../common/services/news.service';
import { Component, OnInit } from '@angular/core';
import { delay, map } from 'rxjs/internal/operators';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-list-news',
  templateUrl: './list-news.component.html',
  styleUrls: ['./list-news.component.scss'],
})
export class ListNewsComponent implements OnInit {

  currentPage: number = 0;
  totalPage: number;
  totalRecord: number;
  totalRecordCurrentPage: number;

  listNews: News[] =[];
  listShowPage: number[] = [];

  listNews2: News[] = [];

   url: Observable<string>;
  constructor(private newsService: NewsService,   private route: ActivatedRoute) {
    this.url= this.route.url.pipe(map(segments => segments.join('')));

  }

  ngOnInit(): void {
    this.getListNews();
    this.getListNews2();
    if (!localStorage.getItem('foo')) { 
      localStorage.setItem('foo', 'no reload') 
      location.reload() 
      window.scroll(0,0);
    } else {
      localStorage.removeItem('foo') 
    }
  }

  getTotalRecordCurrentPage(){
    if (this.totalPage-1 > this.currentPage){
      this.totalRecordCurrentPage = 3;
    } else if ((this.totalPage-1) == this.currentPage){
      this.totalRecordCurrentPage = this.totalRecord - ((this.currentPage)*3);
    }
    return this.totalRecordCurrentPage;
  }

  getDataFromCurrentPage(i: number) {
    this.currentPage = i;
    this.getListNews();
  }

  getDataFromPreviousPage(){
    this.currentPage = this.currentPage - 1;
    if (this.currentPage < 0){
      this.currentPage = 0;
    }
    this.getListNews();
  }

  getDataFromNextPage(){
    this.currentPage = this.currentPage + 1;
    if (this.currentPage > this.totalPage - 1){
      this.currentPage = this.totalPage - 1;
    }
    this.getListNews();
  }

  getListShowPage() { 
    if(this.totalPage >= 3){
      if (this.currentPage == this.totalPage - 1){
        this.listShowPage = [this.currentPage -2, this.currentPage - 1, this.currentPage];
      } else if (this.currentPage == 0){
        this.listShowPage = [this.currentPage, this.currentPage + 1, this.currentPage + 2];
      } else {
        this.listShowPage = [this.currentPage - 1, this.currentPage, this.currentPage + 1];
      }     
    } else if (this.totalPage == 2){
      if (this.currentPage == this.totalPage - 1){
        this.listShowPage = [this.currentPage - 1, this.currentPage];
      } else if (this.currentPage == 0){
        this.listShowPage = [this.currentPage, this.currentPage + 1];
      } else {
        this.listShowPage = [this.currentPage - 1, this.currentPage];
      } 
    } else if (this.totalPage == 1){
      this.listShowPage = [this.currentPage];
    }
    return this.listShowPage;
    
  }

  async getListNews(): Promise<void>{
     let result = await this.newsService.getListNews2Paging(null, this.currentPage);
     this.listNews = result.data.contents;
     this.totalPage = result.data.metadata.totalPage;
     this.totalRecord = result.data.metadata.total;
  }

  async getListNews2(): Promise<void>{
    let result = await this.newsService.getListNews2Paging(null, this.totalPage -1);
    console.log(this.totalPage -1);
    this.listNews2 = result.data.contents;
 }


}
