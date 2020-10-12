import { Component, OnInit, OnChanges, AfterViewInit, Input } from '@angular/core';
import { Mentor } from 'src/app/shared/models/mentor.model';
import { MentorsService } from '../services/mentors.service';
declare var $: any;

@Component({
  selector: 'app-mentors-slider',
  templateUrl: './mentors-slider.component.html',
  styleUrls: ['./mentors-slider.component.scss']
})
export class MentorsSliderComponent implements OnInit, AfterViewInit {
  @Input() imgclass:string;
  listMentor: Mentor[] = []; 

  constructor(private mentorsService: MentorsService) { 
    this.mentorsService.getListMentor(null).then(result => this.listMentor = result.data);
  }

  ngAfterViewInit(): void {
    setTimeout(function(){
    $('#myCarousel').carousel({
      interval: 5000
    });

    $('.mentors-slider .carousel .carousel-item').each(function () {
      var minPerSlide = 4;
      var next = $(this).next();
      if (!next.length) {
        next = $(this).siblings(':first');
      }
      next.children(':first-child').clone().appendTo($(this));

      for (var i = 0; i < minPerSlide; i++) {
        next = next.next();
        if (!next.length) {
          next = $(this).siblings(':first');
        }
        next.children(':first-child').clone().appendTo($(this));
      }
    });
    }, 1000);
  }

  ngOnInit() {
    
  }
}
