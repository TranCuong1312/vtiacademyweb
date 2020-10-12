import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewBusinessComponent } from './review-business.component';

describe('ReviewBusinessComponent', () => {
  let component: ReviewBusinessComponent;
  let fixture: ComponentFixture<ReviewBusinessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewBusinessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewBusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
