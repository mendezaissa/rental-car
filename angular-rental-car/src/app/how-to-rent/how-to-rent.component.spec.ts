import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HowToRentComponent } from './how-to-rent.component';

describe('HowToRentComponent', () => {
  let component: HowToRentComponent;
  let fixture: ComponentFixture<HowToRentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HowToRentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HowToRentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
