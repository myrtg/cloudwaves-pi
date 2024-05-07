import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PfebookComponent } from './pfebook.component';

describe('PfebookComponent', () => {
  let component: PfebookComponent;
  let fixture: ComponentFixture<PfebookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PfebookComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PfebookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
