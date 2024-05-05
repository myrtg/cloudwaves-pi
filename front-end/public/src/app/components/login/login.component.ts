import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/services/authentication.service';
import {AuthenticationRequest} from '../../services/models/authentication-request';
import {TokenService} from '../../services/token/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  siteKey: string;

  captchaChecked: boolean = false;

  authRequest: AuthenticationRequest = {email: '', password: ''};
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService
  ) {
    this.siteKey = '6Le8lc4pAAAAAPGfvO0rw_oml3DIReJqt9HTLAMo';
  }


  handleCaptchaVerify() {
    this.captchaChecked = true;
  }
  login() {
    this.errorMsg = [];
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: (res) => {
        console.log(res)
        this.tokenService.token = res.token as string;
        sessionStorage.setItem('token',this.tokenService.token);
        this.router.navigate(['books']);
      },
      error: (err) => {
        console.log('Error');
        console.log(err);
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.errorMsg.push(err.error.error);
        }
      }
    });
  }

  register() {
    this.router.navigate(['register']);
  }
}
