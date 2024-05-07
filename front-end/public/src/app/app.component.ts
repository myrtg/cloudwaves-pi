import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private activatedroute: ActivatedRoute , private router: Router) {


  }
  isLoginPageOrRegisterPage(): boolean {
    return this.router.url.includes('/login') || this.router.url.includes('/register') ;
  }
  title = 'PFEConnectFront';


//   LoginComponent() {
// return this.activatedroute.firstChild?.snapshot.routeConfig?.path === 'login';
//   }
}
