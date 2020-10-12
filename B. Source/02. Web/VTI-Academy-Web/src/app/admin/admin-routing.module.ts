import { LoginComponent } from '../shared/components/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin.component';
import { PageNotFoundComponent } from '../shared/components/page-not-found/page-not-found.component';
import { AuthGuard } from './common/guards/auth.guard';
import { RedirectIfAuthenticatedGuard } from './common/guards/redirectIfAuthenticated.guard';
import { MainbanneradminComponent } from './modules/mainbanneradmin/mainbanneradmin.component';
import { OtherConfigAdminComponent } from './modules/other-config-admin/other-config-admin.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [RedirectIfAuthenticatedGuard]
  },
  {
    path: '',
    redirectTo: 'about',
    pathMatch: 'full'
  },
  {
    path: '',
    component: AdminComponent,
    children:[
      {
        path:'about',
        loadChildren: ()=> import('./modules/about/about.module').then(m=>m.AboutModule),
        canActivateChild: [AuthGuard]
      },
      {
        path:'reviews',
        loadChildren: ()=> import('./modules/review/review.module').then(m=>m.ReviewModule),
        canActivateChild: [AuthGuard]
      },
      {
        path:'news',
        loadChildren: ()=> import('./modules/news/news.module').then(m=>m.NewsModule),
        canActivateChild: [AuthGuard]
      },
      {
        path:'mentor',
        loadChildren: ()=> import('./modules/mentor/mentor.module').then(m=>m.MentorModule),
        canActivateChild: [AuthGuard]
      },
      {
        path:'enterprise',
        loadChildren: ()=> import('./modules/enterprise-admin/enterprise-admin.module').then(m=>m.EnterpriseAdminModule),
        canActivateChild: [AuthGuard]
      },
      {
        path:'subcourse',
        loadChildren: ()=> import('./modules/subcourse/subcourse.module').then(m=>m.SubcourseModule),
        canActivateChild: [AuthGuard]
      },
      {
        path:'course',
        loadChildren: ()=> import('./modules/courses/courses.module').then(m=>m.CoursesModule),
        canActivateChild: [AuthGuard]
      },
      {
        path:'contact',
        loadChildren: ()=> import('./modules/contact/contact.module').then(m=>m.ContactModule),
        canActivateChild: [AuthGuard]
      },
      {
        path:'mainbanner',
        component: MainbanneradminComponent,
        canActivateChild: [AuthGuard]
      },
      {
        path:'otherconfig',
        component: OtherConfigAdminComponent,
        canActivateChild: [AuthGuard]
      }
    ]
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
