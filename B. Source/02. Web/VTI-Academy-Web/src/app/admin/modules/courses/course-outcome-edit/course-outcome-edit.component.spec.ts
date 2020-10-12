import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseOutcomeEditComponent } from './course-outcome-edit.component';

describe('CourseOutcomeEditComponent', () => {
  let component: CourseOutcomeEditComponent;
  let fixture: ComponentFixture<CourseOutcomeEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CourseOutcomeEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseOutcomeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
