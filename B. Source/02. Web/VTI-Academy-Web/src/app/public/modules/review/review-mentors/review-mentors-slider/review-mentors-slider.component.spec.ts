import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewMentorsSliderComponent } from './review-mentors-slider.component';

describe('ReviewMentorsSliderComponent', () => {
  let component: ReviewMentorsSliderComponent;
  let fixture: ComponentFixture<ReviewMentorsSliderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewMentorsSliderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewMentorsSliderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
