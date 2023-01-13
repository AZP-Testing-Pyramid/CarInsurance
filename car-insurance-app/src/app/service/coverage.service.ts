import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Coverage} from "../model/coverage";

@Injectable({
  providedIn: 'root'
})
export class CoverageService {
  coverageURL = 'http://localhost:8080/coverage';

  constructor(private http: HttpClient) {
  }

  public readOptions(): Observable<Coverage[]> {
    return this.http.get<Coverage[]>(this.coverageURL);
  }
}
