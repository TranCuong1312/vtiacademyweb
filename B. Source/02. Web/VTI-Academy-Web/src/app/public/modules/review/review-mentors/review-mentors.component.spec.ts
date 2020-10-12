import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewMentorsComponent } from './review-mentors.component';

describe('ReviewMentorsComponent', () => {
  let component: ReviewMentorsComponent;
  let fixture: ComponentFixture<ReviewMentorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewMentorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewMentorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
