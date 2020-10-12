import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageIntroComponent } from './image-intro.component';

describe('ImageIntroComponent', () => {
  let component: ImageIntroComponent;
  let fixture: ComponentFixture<ImageIntroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImageIntroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImageIntroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
