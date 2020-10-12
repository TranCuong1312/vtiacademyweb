import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BaseResponse } from 'src/app/shared/models/common-response.model';
import { CommonResponseService } from './common-response.service';

@Injectable({
  providedIn: 'root'
})
export class BusinessTrainingService {

  constructor(private http : HttpClient) { }
}
