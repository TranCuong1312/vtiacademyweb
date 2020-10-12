import { ReviewModule } from './modules/review/review.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminComponent } from './admin.component';
import { SharedModule } from '../shared/shared.module';
import { AdminRoutingModule } from './admin-routing.module';
import { LeftMenuComponent } from './layout/left-menu/left-menu.component';
import { SharedInterceptor } from './common/interceptor/shared.interceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthGuard } from './common/guards/auth.guard';
import { RedirectIfAuthenticatedGuard } from './common/guards/redirectIfAuthenticated.guard';
import { ReviewService } from '../public/common/services/review.service';
import { MainbanneradminComponent } from './modules/mainbanneradmin/mainbanneradmin.component';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { OtherConfigAdminComponent } from './modules/other-config-admin/other-config-admin.component';
import { BusinessTrainingAdminComponent } from './modules/business-training-admin/business-training-admin.component';
 
// import {NgxWebstorageModule} from 'ngx-webstorage';

@NgModule({
  declarations: [AdminComponent, LeftMenuComponent, MainbanneradminComponent, OtherConfigAdminComponent, BusinessTrainingAdminComponent],

  imports: [
    CommonModule,
    AdminRoutingModule,
    SharedModule,
    ReviewModule,
    ReactiveFormsModule
    // NgWebstorageModule.forRoot(),
  ],
  providers: [
    AuthGuard,
    ReviewService,
    RedirectIfAuthenticatedGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: SharedInterceptor,
      multi: true,
    },
  ]
})
export class AdminModule { }
