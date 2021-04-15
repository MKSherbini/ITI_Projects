import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HelloEjbComponent } from './hello-ejb/hello-ejb.component';
import { RouterModule, Routes } from '@angular/router';
import { ConverterComponent } from './converter/converter.component';
import { HitsComponent } from './hits/hits.component';
import { LibraryComponent } from './library/library.component';

const routes: Routes = [
  { path: '', component: HelloEjbComponent },
  { path: 'hello', component: HelloEjbComponent },
  { path: 'converter', component: ConverterComponent },
  { path: 'hits', component: HitsComponent },
  { path: 'library', component: LibraryComponent },
];

@NgModule({
  declarations: [
    HelloEjbComponent,
    ConverterComponent,
    HitsComponent,
    LibraryComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ]
})
export class ServicesModule { }
