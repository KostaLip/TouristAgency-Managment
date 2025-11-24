import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DestinacijaDialogComponent } from './destinacija-dialog.component';

describe('DestinacijaDialogComponent', () => {
  let component: DestinacijaDialogComponent;
  let fixture: ComponentFixture<DestinacijaDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DestinacijaDialogComponent]
    });
    fixture = TestBed.createComponent(DestinacijaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
