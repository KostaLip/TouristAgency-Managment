import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DestinacijaComponent } from './destinacija.component';

describe('DestinacijaComponent', () => {
  let component: DestinacijaComponent;
  let fixture: ComponentFixture<DestinacijaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DestinacijaComponent]
    });
    fixture = TestBed.createComponent(DestinacijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
