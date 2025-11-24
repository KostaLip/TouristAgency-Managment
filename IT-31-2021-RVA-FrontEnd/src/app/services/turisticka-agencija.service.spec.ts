import { TestBed } from '@angular/core/testing';

import { TuristickaAgencijaService } from './turisticka-agencija.service';

describe('TuristickaAgencijaService', () => {
  let service: TuristickaAgencijaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TuristickaAgencijaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
