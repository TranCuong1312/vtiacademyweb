import { ReviewService } from './../../../common/services/review.service';
import { Review } from './../../../../shared/models/review.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-review-student',
  templateUrl: './review-student.component.html',
  styleUrls: ['./review-student.component.scss']
})
export class ReviewStudentComponent implements OnInit {
  
  reviewFromParent: string = 'background-yellow';
  listStudentReview: Review[]= [];

  constructor(private reviewService:ReviewService) { }

  ngOnInit(): void {
    this.getStudentReviews();
    if (!localStorage.getItem('foo')) { 
      localStorage.setItem('foo', 'no reload') 
      location.reload() 
      window.scroll(0,0);
    } else {
      localStorage.removeItem('foo') 
    }
  }

  async getStudentReviews(): Promise<void>{
    let result = await this.reviewService.getStudentReviews(null);
    this.listStudentReview = result.data;

  }
}
