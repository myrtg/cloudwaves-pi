import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
 
  loginForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (!this.loginForm.invalid) {
          
    const username = this.loginForm.value.username;
    const password = this.loginForm.value.password;
    localStorage.setItem("username", username)   
     
    // Your login logic here (e.g., call an authentication service)
    console.log('Login submitted with username:', username, 'and password:', password);
    this.router.navigate(['home']);

    }
  }

}