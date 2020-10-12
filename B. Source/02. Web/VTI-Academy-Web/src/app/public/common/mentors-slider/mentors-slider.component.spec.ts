import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MentorsSliderComponent } from './mentors-slider.component';

describe('MentorsSliderComponent', () => {
  let component: MentorsSliderComponent;
  let fixture: ComponentFixture<MentorsSliderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MentorsSliderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MentorsSliderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
