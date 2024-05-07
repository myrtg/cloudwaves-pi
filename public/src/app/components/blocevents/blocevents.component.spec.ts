import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloceventsComponent } from './blocevents.component';

describe('BloceventsComponent', () => {
  let component: BloceventsComponent;
  let fixture: ComponentFixture<BloceventsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloceventsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloceventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
