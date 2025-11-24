import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TuristickaAgencijaDialogComponent } from './turisticka-agencija-dialog.component';

describe('TuristickaAgencijaDialogComponent', () => {
  let component: TuristickaAgencijaDialogComponent;
  let fixture: ComponentFixture<TuristickaAgencijaDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TuristickaAgencijaDialogComponent]
    });
    fixture = TestBed.createComponent(TuristickaAgencijaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
