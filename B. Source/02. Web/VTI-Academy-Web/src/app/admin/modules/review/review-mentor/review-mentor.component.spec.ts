import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewMentorComponent } from './review-mentor.component';

describe('ReviewMentorComponent', () => {
  let component: ReviewMentorComponent;
  let fixture: ComponentFixture<ReviewMentorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewMentorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewMentorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
