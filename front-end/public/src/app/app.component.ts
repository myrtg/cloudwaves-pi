import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private activatedroute: ActivatedRoute) {
  }
  title = 'PFEConnectFront';


//   LoginComponent() {
// return this.activatedroute.firstChild?.snapshot.routeConfig?.path === 'login';
//   }
}
