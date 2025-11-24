import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AranzmanDialogComponent } from './aranzman-dialog.component';

describe('AranzmanDialogComponent', () => {
  let component: AranzmanDialogComponent;
  let fixture: ComponentFixture<AranzmanDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AranzmanDialogComponent]
    });
    fixture = TestBed.createComponent(AranzmanDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
