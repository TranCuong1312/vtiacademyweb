import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubcourseComponent } from './subcourse.component';

describe('SubcourseComponent', () => {
  let component: SubcourseComponent;
  let fixture: ComponentFixture<SubcourseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubcourseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubcourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
