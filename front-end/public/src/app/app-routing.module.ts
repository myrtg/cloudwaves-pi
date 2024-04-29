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

const routes: Routes = [
  {path:'', component: HomeComponent},
  {path:'login', component:LoginComponent},
  {path:'pfebook', component: PfebookComponent},
  {path:'internship', component: InternshipComponent},
  {path:'faq', component: FaqComponent},
  {path:'events', component: EventsComponent},
  {path:'contactus', component: ContactusComponent},
  {path:'forum', component: ForumComponent},

  {path:'home', component:HomeComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
