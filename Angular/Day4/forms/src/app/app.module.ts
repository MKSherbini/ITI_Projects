import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DemoComponent } from './demo.component';
import { StudentComponent } from './student/student.component';
import { RootComponent } from './root/root.component';
import { TestComponent } from './test/test.component';
import { HeaderComponent } from './header/header.component';
import { MenuComponent } from './shared/menu/menu.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { AboutComponent } from './about/about.component';
import { DetailsComponent } from './details/details.component';
import { StudentItemComponent } from './student-item/student-item.component';
import { StudentCreateComponent } from './student-create/student-create.component';

@NgModule({
  declarations: [
    AppComponent, DemoComponent, StudentComponent, RootComponent, TestComponent, HeaderComponent,
    MenuComponent, NotFoundComponent, AboutComponent, DetailsComponent, StudentItemComponent, StudentCreateComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [RootComponent]
})
export class AppModule { }
