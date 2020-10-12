import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubcourseEditComponent } from './subcourse-edit.component';

describe('SubcourseEditComponent', () => {
  let component: SubcourseEditComponent;
  let fixture: ComponentFixture<SubcourseEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubcourseEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubcourseEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
