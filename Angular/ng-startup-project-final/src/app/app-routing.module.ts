import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './components/shared/layout/layout.component';

const routes: Routes = [
  {
    path: '', 
    component:LayoutComponent,
    loadChildren: () => import('../app/components/profile/profile.module').then(m => m.ProfileModule)
  },
  {
    path: 'user', 
    component:LayoutComponent,
    loadChildren: () => import('../app/components/user/user.module').then(m => m.UserModule)
  },
  {
    path: 'task', 
    component:LayoutComponent,
    loadChildren: () => import('../app/components/task/task.module').then(m => m.TaskModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
exports: [RouterModule]
})
export class AppRoutingModule { }
