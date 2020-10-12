import { TestBed } from '@angular/core/testing';

import { BusinessTrainingService } from './business-training.service';

describe('BusinessTrainingService', () => {
  let service: BusinessTrainingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BusinessTrainingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
