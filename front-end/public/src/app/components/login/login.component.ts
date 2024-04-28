import { Component } from '@angular/core';
import {AuthenticationRequest} from "../../../services/models/authentication-request";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../../services/services/authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  authRequest: AuthenticationRequest = {email: '', password: ''};
  errorMsg: Array<string> = [];


  constructor(
    private router : Router,
    private authService: AuthenticationService
    //mezel mezel
  )
    {}
  login() {
    this.errorMsg = [];
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: (res) => {
        // this.tokenService.token = res.token as string;
        this.router.navigate(['home']);
      },
      error: (err) => {
        console.log(err);
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.errorMsg.push(err.error.errorMsg);
        }
      }
    });
  }


  // register() {
  //   this.router.navigate(['register'])
  //
  // }
}
