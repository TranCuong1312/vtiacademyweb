import { TestBed } from '@angular/core/testing';

import { OtherConfigService } from './other-config.service';

describe('OtherConfigService', () => {
  let service: OtherConfigService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OtherConfigService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
