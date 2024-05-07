import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChatComponent } from './components/chat/chat.component';
import { AsideComponent } from './components/aside/aside.component';
import { HomeComponent } from './components/home/home.component';
import { CategorieComponent } from './components/categorie/categorie.component';
import { EventsComponent } from './components/events/events.component';
import { PfebookComponent } from './components/pfebook/pfebook.component';
import { InternshipComponent } from './components/internship/internship.component';
import { UserComponent } from './components/user/user.component';
import { ForumComponent } from './components/forum/forum.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {path:'', component: LoginComponent},
  {path:'home', component: HomeComponent},
  {path:'chat', component: ChatComponent},
 {path:'aside', component: AsideComponent},
  {path:'events',component:EventsComponent},
  {path:'pfebook',component:PfebookComponent},
  {path:'internship',component:InternshipComponent},
  {path:'user',component:UserComponent},
  {path:'forum',component:ForumComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
