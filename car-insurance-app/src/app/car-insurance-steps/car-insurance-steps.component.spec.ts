import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarInsuranceStepsComponent } from './car-insurance-steps.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MatStepperModule} from "@angular/material/stepper";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatListModule} from "@angular/material/list";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";

describe('CarInsuranceStepsComponent', () => {
  let component: CarInsuranceStepsComponent;
  let fixture: ComponentFixture<CarInsuranceStepsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        FormsModule,
        ReactiveFormsModule,
        HttpClientTestingModule,
        MatStepperModule,
        MatInputModule,
        MatButtonModule,
        MatListModule,
        MatDatepickerModule,
        MatOptionModule,
        MatSelectModule,
        NoopAnimationsModule
      ],
      declarations: [ CarInsuranceStepsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarInsuranceStepsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create component', () => {
    expect(component).toBeTruthy();
  });
});
