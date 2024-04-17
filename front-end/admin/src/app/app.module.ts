import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AsideComponent } from './components/aside/aside.component';

import { ChatComponent } from './components/chat/chat.component';
import { HomeComponent } from './components/home/home.component';
import { CategorieComponent } from './components/categorie/categorie.component';
import { ForumComponent } from './components/forum/forum.component';
import { EventsComponent } from './components/events/events.component';
import { PfebookComponent } from './components/pfebook/pfebook.component';
import { InternshipComponent } from './components/internship/internship.component';
import { UserComponent } from './components/user/user.component';

@NgModule({
  declarations: [
    AppComponent,
    AsideComponent,
   
    ChatComponent,
        HomeComponent,
        CategorieComponent,
        ForumComponent,
        EventsComponent,
        PfebookComponent,
        InternshipComponent,
        UserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
