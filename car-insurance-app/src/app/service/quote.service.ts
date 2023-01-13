import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {QuoteData} from "../model/quote-data";
import {forkJoin, map, Observable} from "rxjs";


interface TaxRequest {
  power: number;
  fuelType: string;
  co2Emissions: number;
  firstRegistration: Date;
}

interface TaxResponse {
  tax: number
}

interface PremiumRequest {
  power: number;
  bonusMalus: number;
  zipCode: number;
  coverageId?: number;
}

interface PremiumResponse {
  premium: number
}

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class QuoteService {
  taxURL = 'http://localhost:8080/tax';
  premiumURL = 'http://localhost:8080/premium';

  constructor(private http: HttpClient) {
  }

  public calculateQuote(co2Emissions: number,
                        power: number,
                        fuelType: string,
                        firstRegistration: Date,
                        bonusMalus: number,
                        zipCode: number,
                        coverageId?: number): Observable<QuoteData> {

    const premiumRequest: PremiumRequest = {power: power, bonusMalus: bonusMalus, zipCode: zipCode, coverageId: coverageId};
    let premium = this.http.post<PremiumResponse>(this.premiumURL, premiumRequest, httpOptions);

    const taxRequest: TaxRequest = {
      power: power,
      fuelType: fuelType,
      co2Emissions: co2Emissions,
      firstRegistration: firstRegistration
    };
    let tax = this.http.post<TaxResponse>(this.taxURL, taxRequest, httpOptions);

    return forkJoin([tax, premium])
      .pipe(
        map(arr => {
            return {
              tax: arr[0].tax,
              premium: arr[1].premium
            } as QuoteData;
          }
        ));
  }

}
