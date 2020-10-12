import { NewsService } from '../../../common/services/news.service';
import { News } from './../../../../shared/models/news.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-new-detail',
  templateUrl: './new-detail.component.html',
  styleUrls: ['./new-detail.component.scss'],
})
export class NewDetailComponent implements OnInit {
  newsDetail: News;
  listNews: News[] = [];
  listNewsRandom: News[] = [];
  id: number;
  constructor(
    private route: ActivatedRoute,
    private newsService: NewsService
  ) {}

  ngOnInit(): void {
    this.getNewsDetail();
    this.getNewsRandom();
  }

  async getNewsDetail(): Promise<void> {
    this.route.params.subscribe((params) => {
      this.id = params['id'];
    });
    let result = await this.newsService.getNewsDetail(null, this.id);
    this.newsDetail = result.data;
  }

  async getNewsRandom(): Promise<void>{
    let result = await this.newsService.getNewsRandom(null);
    this.listNewsRandom = result.data;
  }
}
