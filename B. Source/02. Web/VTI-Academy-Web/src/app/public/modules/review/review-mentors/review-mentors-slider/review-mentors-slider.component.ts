import { Component, OnInit } from '@angular/core';
import {Mentor} from '../../../../../shared/models/mentor.model';
import { MentorsService } from 'src/app/public/common/services/mentors.service';
import { Review } from 'src/app/shared/models/review.model';

@Component({
  selector: 'app-review-mentors-slider',
  templateUrl: './review-mentors-slider.component.html',
  styleUrls: ['./review-mentors-slider.component.scss']
})
export class ReviewMentorsSliderComponent implements OnInit {
  listReview: Review[] = [];

  constructor(private mentorsService:MentorsService) { }

  ngOnInit(){
    this.getListMentor();
  }

  async getListMentor(): Promise<void>{
    let result = await this.mentorsService.getListMentorReview(null);
    this.listReview= result.data;
  }

}
