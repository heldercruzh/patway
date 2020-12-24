import { TestBed } from '@angular/core/testing';

import { UfService } from './uf.service';

describe('UfService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UfService = TestBed.get(UfService);
    expect(service).toBeTruthy();
  });
});
