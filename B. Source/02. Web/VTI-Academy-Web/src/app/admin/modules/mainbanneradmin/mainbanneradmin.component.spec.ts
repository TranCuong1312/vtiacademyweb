import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainbanneradminComponent } from './mainbanneradmin.component';

describe('MainbanneradminComponent', () => {
  let component: MainbanneradminComponent;
  let fixture: ComponentFixture<MainbanneradminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainbanneradminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainbanneradminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
