import { TestBed } from '@angular/core/testing';

import { MainbannerService } from './mainbanner.service';

describe('MainbannerService', () => {
  let service: MainbannerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MainbannerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
