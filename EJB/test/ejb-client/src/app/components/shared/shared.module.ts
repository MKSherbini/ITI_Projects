import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './menu/menu.component';
import { LayoutComponent } from './layout/layout.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    MenuComponent,
    LayoutComponent
  ],
  imports: [
    RouterModule,
    CommonModule,
    HttpClientModule
  ],
  exports: [
    MenuComponent,
    HttpClientModule
  ]
})
export class SharedModule { }
