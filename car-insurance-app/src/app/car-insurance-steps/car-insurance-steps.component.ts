import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {QuoteService} from "../service/quote.service";
import {CoverageService} from "../service/coverage.service";
import {Coverage} from "../model/coverage";

interface InsuranceQuote {
  name: string;
  dayOfBirth: Date;
  zipCode: number;
  firstRegistration: Date;
  fuelType: string;
  co2Emissions: number;
  power: number;
  startDate: Date;
  coverage: string;
  bonusMalusLevel: string;
  tax: number;
  premium: number;
}

@Component({
  selector: 'app-car-insurance-steps',
  templateUrl: './car-insurance-steps.component.html',
  styleUrls: ['./car-insurance-steps.component.css']
})
export class CarInsuranceStepsComponent implements OnInit {

  isLinear = true;
  carDetailsFormGroup!: FormGroup;
  contractDetailsFormGroup!: FormGroup;
  personDetailsFormGroup!: FormGroup;

  fuelTypeTable = [
    {value: 'gasoline', viewValue: 'Gasoline'},
    {value: 'diesel', viewValue: 'Diesel'},
    {value: 'hybrid', viewValue: 'Hybrid'},
    {value: 'electricity', viewValue: 'Electricity'}
  ];

  bonusMalusTable = [
    {value: '0', viewValue: 'Bonus-0'},
    {value: '1', viewValue: 'Bonus-1'},
    {value: '2', viewValue: 'Bonus-2'},
    {value: '3', viewValue: 'Bonus-3'},
    {value: '4', viewValue: 'Bonus-4'},
    {value: '5', viewValue: 'Bonus-5'},
    {value: '6', viewValue: 'Bonus-6'},
    {value: '7', viewValue: 'Bonus-7'},
    {value: '8', viewValue: 'Bonus-8'},
    {value: '9', viewValue: '9'},
    {value: '10', viewValue: 'Malus-10'},
    {value: '11', viewValue: 'Malus-11'},
    {value: '12', viewValue: 'Malus-12'},
    {value: '13', viewValue: 'Malus-13'},
    {value: '14', viewValue: 'Malus-14'},
    {value: '15', viewValue: 'Malus-15'},
    {value: '16', viewValue: 'Malus-16'},
    {value: '17', viewValue: 'Malus-17'}
  ];
  coverageTable: Coverage[];
  quote: InsuranceQuote | null;

  constructor(private _formBuilder: FormBuilder, private quoteService: QuoteService, private coverageService: CoverageService) {
    this.quote = null;
    this.coverageTable = [];
  }

  ngOnInit() {

    this.coverageService.readOptions()
      .subscribe({
        next: value => this.coverageTable = value,
        error: err => console.log(err)
      })

    this.carDetailsFormGroup = this._formBuilder.group({
      firstRegistration: ['', Validators.required],
      fuelType: ['', Validators.required],
      co2Emissions: ['', Validators.required],
      power: ['', Validators.required],
    });
    this.contractDetailsFormGroup = this._formBuilder.group({
      startDate: [new Date(), Validators.required],
      coverage: ['', Validators.required],
      bonusMalusLevel: ['', Validators.required]
    });
    this.personDetailsFormGroup = this._formBuilder.group({
      name: ['', Validators.required],
      dayOfBirth: ['', Validators.required],
      zipCode: ['', Validators.required]
    });
  }

  submit() {
    console.log(this.quote);
  }

  calculate() {
    this.quoteService
      .calculateQuote(this.carDetailsFormGroup.value.co2Emissions,
        this.carDetailsFormGroup.value.power,
        this.carDetailsFormGroup.value.fuelType,
        this.carDetailsFormGroup.value.firstRegistration,
        this.contractDetailsFormGroup.value.bonusMalusLevel,
        this.personDetailsFormGroup.value.zipCode,
        this.contractDetailsFormGroup.value.coverage,
      )
      .subscribe({
        next: result => this.quote = {
          ...this.personDetailsFormGroup.value,
          ...this.contractDetailsFormGroup.value,
          ...this.carDetailsFormGroup.value,
          premium: result.premium,
          tax: result.tax
        },
        error: err => {
          console.log(err);
        }
      });
  }

}
