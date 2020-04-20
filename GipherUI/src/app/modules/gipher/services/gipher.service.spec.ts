import { TestBed } from '@angular/core/testing';

import { GipherService } from './gipher.service';

describe('GipherService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GipherService = TestBed.get(GipherService);
    expect(service).toBeTruthy();
  });
});
