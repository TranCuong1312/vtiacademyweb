import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnterpriseAdminComponent } from './enterprise-admin.component';

describe('EnterpriseAdminComponent', () => {
  let component: EnterpriseAdminComponent;
  let fixture: ComponentFixture<EnterpriseAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnterpriseAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnterpriseAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
