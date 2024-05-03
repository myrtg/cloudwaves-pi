import {ChangeDetectionStrategy, Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/services/authentication.service';
import {RegistrationRequest} from '../../services/models/registration-request';
import {AuthenticationResponse} from "../../services/models/authentication-response";
// import { MatSlideToggleChange } from "@angular/material";
// import { Title } from "@angular/platform-browser";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  encapsulation: ViewEncapsulation.None,
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class RegisterComponent implements OnInit {
  siteKey: string;

  registerRequest: RegistrationRequest = {
    email: '',
    firstname: '',
    lastname: '',
    password: '',
    mobile: '',
    mfaEnabled: ''
  }
  errorMsg: Array<string> = [];
  authResponse: AuthenticationResponse = {};



  constructor(
    private router: Router,
    private authService: AuthenticationService,
  ) {
    this.siteKey = '6Le8lc4pAAAAAPGfvO0rw_oml3DIReJqt9HTLAMo';
  }

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }


  login() {
    this.router.navigate(['login']);
  }

  register() {
    this.errorMsg = [];
    this.authService.register({
      body: this.registerRequest
    })
      .subscribe({
        next: () => {
          this.router.navigate(['activate-account']);
        },
        error: (err) => {
          this.errorMsg = err.error.validationErrors;
        }
      });
  }
}
