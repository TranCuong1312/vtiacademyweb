import { ReactiveFormsModule } from '@angular/forms';
import { EnterpriseAdminRoutingModule } from './enterprise-admin-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EnterpriseAdminComponent } from './enterprise-admin.component';

@NgModule({
  declarations: [EnterpriseAdminComponent],
  imports: [
    CommonModule,
    EnterpriseAdminRoutingModule,
    ReactiveFormsModule
  ]
})
export class EnterpriseAdminModule { }
