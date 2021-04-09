import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DemoComponent } from './demo.component';
import { EmployeeComponent } from './employee/employee.component';
import { RootComponent } from './root/root.component';
import { TestComponent } from './test/test.component';

@NgModule({
  declarations: [
    AppComponent, DemoComponent, EmployeeComponent, RootComponent, TestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [RootComponent]
})
export class AppModule { }
