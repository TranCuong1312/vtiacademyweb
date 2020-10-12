import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessTrainingAdminComponent } from './business-training-admin.component';

describe('BusinessTrainingAdminComponent', () => {
  let component: BusinessTrainingAdminComponent;
  let fixture: ComponentFixture<BusinessTrainingAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BusinessTrainingAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BusinessTrainingAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
