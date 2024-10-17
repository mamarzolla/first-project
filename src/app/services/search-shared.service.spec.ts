import { TestBed } from '@angular/core/testing';

import { SearchSharedService } from './search-shared.service';

describe('SearchSharedService', () => {
  let service: SearchSharedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchSharedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
