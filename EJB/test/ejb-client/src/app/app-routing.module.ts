import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './components/shared/layout/layout.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    loadChildren: () => import('../app/components/services/services.module').then(m => m.ServicesModule)
  },
  {
    path: 'services',
    component: LayoutComponent,
    loadChildren: () => import('../app/components/services/services.module').then(m => m.ServicesModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
