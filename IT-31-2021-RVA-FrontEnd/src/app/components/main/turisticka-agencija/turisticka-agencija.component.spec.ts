import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TuristickaAgencijaComponent } from './turisticka-agencija.component';

describe('TuristickaAgencijaComponent', () => {
  let component: TuristickaAgencijaComponent;
  let fixture: ComponentFixture<TuristickaAgencijaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TuristickaAgencijaComponent]
    });
    fixture = TestBed.createComponent(TuristickaAgencijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
