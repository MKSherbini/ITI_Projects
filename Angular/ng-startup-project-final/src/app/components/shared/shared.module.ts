import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LayoutComponent } from './layout/layout.component';
import { MenuComponent } from './menu/menu.component';
import { RouterModule } from '@angular/router';
import { RatingComponent } from './rating/rating.component';
import { HttpClientModule } from '@angular/common/http';
import { MaxLengthPipe } from 'src/app/pipes/max-length.pipe';
import { AllowCharOnlyDirective } from 'src/app/directives/allow-char-only.directive';


@NgModule({
  declarations: [
    LayoutComponent, MenuComponent,RatingComponent
    ,MaxLengthPipe,AllowCharOnlyDirective
  ],
  imports: [
  CommonModule,FormsModule,
    ReactiveFormsModule,RouterModule,HttpClientModule
  ],
  exports:[MenuComponent,MaxLengthPipe,RatingComponent,LayoutComponent,FormsModule,
    ReactiveFormsModule,HttpClientModule,AllowCharOnlyDirective]
})
export class SharedModule { }
