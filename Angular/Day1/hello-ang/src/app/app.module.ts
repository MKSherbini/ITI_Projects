import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DemoComponent } from './demo.component';
import { EmployeeComponent } from './employee/employee.component';
import { RootComponent } from './root/root.component';

@NgModule({
  declarations: [
    AppComponent, DemoComponent, EmployeeComponent, RootComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [RootComponent]
})
export class AppModule { }
