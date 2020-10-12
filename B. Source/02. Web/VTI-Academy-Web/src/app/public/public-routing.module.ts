import { RedirectIfUserAuthenticatedGuard } from './common/guards/user-auth.guard';
import { LoginComponent } from '../shared/components/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PublicComponent } from '../public/public.component';
import { PageNotFoundComponent } from '../shared/components/page-not-found/page-not-found.component';

const routes: Routes = [

  {
    path : '',
    redirectTo : 'home',
    pathMatch : 'full'
  },
  {
    path: '',
    component: PublicComponent,
    children: [
      {
        path: 'home',
        pathMatch: 'full',
        loadChildren: () => import('../public/modules/home/home.module').then(m => m.HomeModule)
      },
      {
        path: 'about-us',
        loadChildren: () => import('../public/modules/about/about.module').then(m => m.AboutModule)
      },
      {
        path: 'courses',
        loadChildren: () => import('../public/modules/courses/courses.module').then(m => m.CoursesModule)
      },
      {
        path: 'reviews',
        loadChildren: () => import('../public/modules/review/review.module').then(m => m.ReviewModule)
      },
      {
        path: 'news',
        loadChildren: () => import('../public/modules/news/news.module').then(m => m.NewsModule)
      },
      {
        path: 'login',
        component: LoginComponent,
        canActivate : [RedirectIfUserAuthenticatedGuard]
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
export class PublicRoutingModule { }
