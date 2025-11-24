import { TestBed } from '@angular/core/testing';

import { DestinacijaService } from './destinacija.service';

describe('DestinacijaService', () => {
  let service: DestinacijaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DestinacijaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
