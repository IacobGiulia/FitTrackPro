import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable, tap } from "rxjs";

export interface LoginRequest{
  email: string;
  password: string;
}

export interface LoginResponse{
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService{

  private readonly apiUrl='http://localhost:8080/api/auth';

  constructor(private http: HttpClient){}

  login(request: LoginRequest): Observable<LoginResponse>{
    return this.http
      .post<LoginResponse>(`${this.apiUrl}/login`, request)
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
        })
      );
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  logout(): void{
    localStorage.removeItem('token');
  }

  isLoggedIn(): boolean{
    return !!localStorage.getItem('token');
  }
}