import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjoutCandidatureComponent } from './ajout-candidature.component';

describe('AjoutCandidatureComponent', () => {
  let component: AjoutCandidatureComponent;
  let fixture: ComponentFixture<AjoutCandidatureComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AjoutCandidatureComponent]
    });
    fixture = TestBed.createComponent(AjoutCandidatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
