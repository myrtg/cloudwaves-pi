import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjoutCondidatureComponent } from './ajout-condidature.component';

describe('AjoutCondidatureComponent', () => {
  let component: AjoutCondidatureComponent;
  let fixture: ComponentFixture<AjoutCondidatureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AjoutCondidatureComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjoutCondidatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
