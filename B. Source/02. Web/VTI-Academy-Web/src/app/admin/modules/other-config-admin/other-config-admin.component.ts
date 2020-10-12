import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OtherConfig } from 'src/app/shared/models/other-config.model';
import { AuthService } from 'src/app/shared/services/auth.service';
import { OtherConfigService } from 'src/app/public/common/services/other-config.service';
declare var $ : any;

@Component({
  selector: 'app-other-config-admin',
  templateUrl: './other-config-admin.component.html',
  styleUrls: ['./other-config-admin.component.scss']
})
export class OtherConfigAdminComponent implements OnInit {

  otherConfig: OtherConfig;
  otherConfigEditForm: FormGroup;

  constructor(
    private otherConfigService: OtherConfigService,
    private build: FormBuilder,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.getOtherConfig();
    console.log('other config: ', this.otherConfig);
    
    this.otherConfigEditForm = this.build.group({
      training_program_img: ['', Validators.required],
      business_training_text: ['', Validators.required]
    })
  }

  getOtherConfig(){
    this.otherConfigService.getOtherConfig(null).then(
      (data) => {     
        this.otherConfig = data[0];
        console.log(this.otherConfig);
            
      }
    );
  }

  file: any;
  already = false;

  onSelectedFile(event) {
    this.file = event.target.files[0];
    if(this.file) {
      this.already = true;
    }
  }

  onOpenOtherConfigEditForm(otherConfig: OtherConfig){
    this.otherConfigEditForm.setValue({
      training_program_img: otherConfig.training_program_img,
      business_training_text: otherConfig.business_training_text
    })
  }

  formEdit : OtherConfig;
  async submitEditOtherConfig() {
    if(confirm('Are you sure to edit ?')){
      this.formEdit = new OtherConfig(
        this.otherConfigEditForm.get('training_program_img').value,
        this.otherConfigEditForm.get('business_training_text').value
      );

    const formData = new FormData();
    formData.append("otherConfig", JSON.stringify(this.formEdit));
    let result : any;
      
    if(this.already) {
      formData.append("image", this.file);
      result = await this.otherConfigService.editOtherConfigHaveImage(formData);
    }
    else {
      formData.delete("image");
      result = await this.otherConfigService.editOtherConfig(formData);
    }
    console.log('result: ', result);
    
    if (!result) {
      this.authService.showToastr(2, "Update Fail", "Something Wrong :<")
    } else {
      this.authService.showToastr(1, "This Other Config has been updated", "Update Success")
      $('#editEmployeeModal').modal('hide');
    }
  }
  this.getOtherConfig();
  }
}
