import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorSearchComponent } from './doctor-search.component';

describe('DoctorSearchComponent', () => {
  let component: DoctorSearchComponent;
  let fixture: ComponentFixture<DoctorSearchComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DoctorSearchComponent]
    });
    fixture = TestBed.createComponent(DoctorSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
