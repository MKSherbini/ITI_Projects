import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from './index/index.component';
import { DetailsComponent } from './details/details.component';
import { Routes, RouterModule } from '@angular/router';
import { SharedModule } from './../shared/shared.module';
import { AuthGuard } from 'src/app/guards/auth.guard';
const routes: Routes = [
  {path:'index',component:IndexComponent,canActivate:[AuthGuard]},
  {path:'details/:id',component:DetailsComponent},
  {path:'',component:IndexComponent}
];

@NgModule({
  declarations: [
    IndexComponent,
    DetailsComponent
  ],
  imports: [
 
  CommonModule,RouterModule.forChild(routes),SharedModule
  ]
})
export class TaskModule { }
