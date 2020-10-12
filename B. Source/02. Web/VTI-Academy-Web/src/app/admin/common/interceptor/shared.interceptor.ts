import { AuthService } from './../../../shared/services/auth.service';
import { Injectable } from '@angular/core';
import { HttpErrorResponse, HttpHandler, HttpInterceptor, HttpRequest, HttpEvent } from '@angular/common/http';
import { catchError } from 'rxjs/internal/operators';
import { throwError, Observable } from 'rxjs/index';

@Injectable()
export class SharedInterceptor implements HttpInterceptor {

  constructor(
    private auth: AuthService) {
  }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${this.auth.getToken()}`,
        
      },
    });
    return next.handle(req)
      .pipe(
        catchError((err: HttpErrorResponse) => {
          if (err.status === 401) {
            this.auth.logout();
          }
          return throwError(err);
        }),
      )
  }
}
