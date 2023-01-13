import { TestBed } from '@angular/core/testing';

import { CoverageService } from './coverage.service';
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {HttpClient} from "@angular/common/http";

describe('CoverageService', () => {
  let httpClient;
  let httpTestingController;
  let service: CoverageService;

  beforeEach(() => {

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        CoverageService
      ]
    });

    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(CoverageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
