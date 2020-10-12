import { ReviewService } from "./../../../common/services/review.service";
import { Component, OnInit } from "@angular/core";
import { Review } from "src/app/shared/models/review.model";
import { OtherConfig } from 'src/app/shared/models/other-config.model';
import { OtherConfigService } from 'src/app/public/common/services/other-config.service';

@Component({
  selector: "app-review-business",
  templateUrl: "./review-business.component.html",
  styleUrls: ["./review-business.component.scss"],
})
export class ReviewBusinessComponent implements OnInit {

  otherConfig: OtherConfig;

  constructor(
    private reviewService: ReviewService,
    private otherConfigService: OtherConfigService
  ) {}

  ngOnInit(): void {
    this.getOtherConfig();
    
    if (!localStorage.getItem('foo')) { 
      localStorage.setItem('foo', 'no reload') 
      location.reload() 
      window.scroll(0,0);
    } else {
      localStorage.removeItem('foo') 
    }
  }

  async getOtherConfig(): Promise<void>{
    let result = await this.otherConfigService.getOtherConfigPublic(null);
    this.otherConfig = result.data[0];
  }
}
