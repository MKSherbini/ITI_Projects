import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { DetailsComponent } from './details/details.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { StudentCreateComponent } from './student-create/student-create.component';
import { StudentComponent } from './student/student.component';

const routes: Routes = [
  { path: "students", component: StudentComponent },
  { path: "about", component: AboutComponent, },
  { path: "details/:id", component: DetailsComponent, },
  { path: "contact", component: ContactComponent, },
  { path: "create", component: StudentCreateComponent, },
  { path: "", component: StudentComponent, },
  { path: "**", component: NotFoundComponent, },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
