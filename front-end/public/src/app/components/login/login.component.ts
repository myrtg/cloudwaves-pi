import {Component, OnInit} from '@angular/core';
import {AuthenticationRequest} from "../../../services/models/authentication-request";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../../services/services/authentication.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {
  // constructor(private router: Router , private authenticationService: AuthenticationService){}
  // ngOnInit() {
  // }
  // loginForm = new FormGroup({
  //   email: new FormControl('ayaghattas@gmail.com', [Validators.required, Validators.email]),
  //   password: new FormControl('password10', [Validators.required])
  // });
  // Login(){
  //
  //   const payload: any = {
  //     email: this.loginForm.value.email || '',
  //     password: this.loginForm.value.password || ''
  //   };
  //   this.authenticationService.authenticate(payload).subscribe({
  //     next: (res) => {
  //       console.log(res);
  //       this.router.navigate(['home']);
  //     },
  //     error: (err) => {
  //       console.log(err);
  //     }
  //   });
  //   }


  authRequest: AuthenticationRequest = { email: 'ayaghattas@gmail.com', password: 'password10' };

  errorMsg: Array<string> = [];


  constructor(
    private router : Router,
    private authService: AuthenticationService
  )
    {}
  login() {
    this.errorMsg = [];
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: (res) => {
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
