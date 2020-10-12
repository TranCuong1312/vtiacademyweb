import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OtherConfigAdminComponent } from './other-config-admin.component';

describe('OtherConfigAdminComponent', () => {
  let component: OtherConfigAdminComponent;
  let fixture: ComponentFixture<OtherConfigAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OtherConfigAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OtherConfigAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
