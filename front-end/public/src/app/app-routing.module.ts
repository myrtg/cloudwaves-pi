import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PfebookComponent } from './components/pfebook/pfebook.component';
import { InternshipComponent } from './components/internship/internship.component';
import { FaqComponent } from './components/faq/faq.component';
import { EventsComponent } from './components/events/events.component';
import { ContactusComponent } from './components/contactus/contactus.component';
import { ForumComponent } from './components/forum/forum.component';
import {LoginComponent} from "./components/login/login.component";
import {CustomRoute} from "./custom-route";
import {RegisterComponent} from "./components/register/register.component";
import {ActivateAccountComponent} from "./components/activate-account/activate-account.component";
import { ChatComponent } from './components/chat/chat.component';


const routes: Routes = [
  {path:'home', component: HomeComponent},
  {path:'', component:LoginComponent},

  {path:'pfebook', component: PfebookComponent},
  {path:'internship', component: InternshipComponent},
  {path:'faq', component: FaqComponent},
  {path:'events', component: EventsComponent},
  {path:'contactus', component: ContactusComponent},
  {path:'forum', component: ForumComponent},
  {path:'chat', component: ChatComponent},
  {path:'register', component:RegisterComponent},
  {path:'activate-account', component:ActivateAccountComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
