import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-review-mentors',
  templateUrl: './review-mentors.component.html',
  styleUrls: ['./review-mentors.component.scss']
})
export class ReviewMentorsComponent implements OnInit {
constructor(){}

ngOnInit(){
  if (!localStorage.getItem('foo')) { 
    localStorage.setItem('foo', 'no reload') 
    location.reload() 
    window.scroll(0,0);
  } else {
    localStorage.removeItem('foo') 
  }
}



}
