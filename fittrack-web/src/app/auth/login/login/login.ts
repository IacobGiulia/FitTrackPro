import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../auth.service';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {

  email='';
  password='';

  constructor(private authService: AuthService){}

  login(): void {
    this.authService.login({
      email: this.email,
      password: this.password
    }).subscribe({
      next: () => {
        console.log('Login successful!');
      },
      error: (error) => {
        console.error('Login failed!', error);
      }
    });
  }
}
