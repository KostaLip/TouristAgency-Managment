import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AranzmanComponent } from './aranzman.component';

describe('AranzmanComponent', () => {
  let component: AranzmanComponent;
  let fixture: ComponentFixture<AranzmanComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AranzmanComponent]
    });
    fixture = TestBed.createComponent(AranzmanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
