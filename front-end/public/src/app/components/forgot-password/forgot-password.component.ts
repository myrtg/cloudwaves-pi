// forgot-password.component.ts

import { Component } from '@angular/core';
import { ForgotPasswordService } from '../../services/services/forgot-password.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  siteKey: string;

  email: string='';
  errorMessage!: string;
  captchaChecked: boolean = false;


  constructor(private forgotPasswordService: ForgotPasswordService,private router: Router,) {
    this.siteKey = '6Le8lc4pAAAAAPGfvO0rw_oml3DIReJqt9HTLAMo';
  }

  onSubmit() {
    this.forgotPasswordService.forgotPassword(this.email)
      .subscribe(
        () => {
          // Handle success
          console.log('Password reset email sent successfully');
          this.router.navigate(['/login']);
        },
        (error) => {
          // Handle error
          this.errorMessage = error.message;
        }
      );
  }
}
