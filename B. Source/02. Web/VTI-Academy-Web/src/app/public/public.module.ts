import { AuthUserService } from './common/services/auth-user.service';
import { PublicRoutingModule } from './public-routing.module';
import { NewsModule } from './modules/news/news.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';

import { PublicComponent } from './public.component';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';

import { CommonPublicModule } from './common/common-public.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { SocialComponent } from './layout/social/social.component';
import { RedirectIfUserAuthenticatedGuard } from './common/guards/user-auth.guard';

@NgModule({
  declarations: [
    PublicComponent,
    HeaderComponent,
    FooterComponent,
    SocialComponent
    ],
  imports: [
    CommonModule,
    PublicRoutingModule,
    SharedModule,
    CommonPublicModule,
    FontAwesomeModule,
    NewsModule
    ],
  providers: [
    RedirectIfUserAuthenticatedGuard,
    AuthUserService
  ],

  exports: [],
})
export class PublicModule {}
