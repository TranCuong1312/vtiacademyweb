import { Component, OnInit } from '@angular/core';
import { AboutService } from 'src/app/public/common/services/about.service';
import { About } from 'src/app/shared/models/about.model';
import { FormGroup, FormBuilder } from '@angular/forms';
import { AuthService } from 'src/app/shared/services/auth.service';
import { AboutRequest } from '../../common/models/about-request';
import { IntroRequest } from '../../common/models/intro-request';
import { Intro } from 'src/app/shared/models/intro.model';
import { IntroService } from 'src/app/public/common/services/intro.service';
declare var $:any;
@Component({
  selector: 'app-intro',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent implements OnInit {
  id : number;
  editForm : FormGroup;
  editFormIntro : FormGroup;
  about : About[];
  intro : Intro[];
  constructor(private aboutSevice : AboutService, private introService : IntroService
    ,private build : FormBuilder,private authService : AuthService) { }

  ngOnInit(): void {
    this.getAbout();

    this.getListIntro();

    this.editForm = this.build.group({
      id : [''],
      description : [''],
      video : [''],
      titleVideo : [''],
      descriptionVideo : [''],
    });

    this.editFormIntro = this.build.group({
      intro_id : [''],
      name : [''],
      intro_des : [''],
    });
  }
  async updateAbout(){
    if(confirm('Are you sure to edit ?')){
      let result = await this.aboutSevice.editAbout(Number(this.editForm.get('id').value),
        new AboutRequest(
          this.editForm.get('description').value,
          this.editForm.get('video').value,
          this.editForm.get('titleVideo').value,
          this.editForm.get('descriptionVideo').value
        )
      );
      if(result.type === "ERROR"){
        this.authService.showToastr(2,"Update Fail","Something Wrong :<")
      }else{
        this.authService.showToastr(1,"This News has been updated","Update Success")
        $('#editEmployeeModal').modal('hide');
      }
      this.getAbout();
    }
  }
  async getAbout(): Promise<void>{
    let result = await this.aboutSevice.getAbout(null);
    this.about = result.data;
  }
  openEditForm(aboutUs : About){
    this.editForm.setValue({
      id : aboutUs.id,
      description : aboutUs.description,
      video : aboutUs.video,
      titleVideo : aboutUs.titleVideo,
      descriptionVideo : aboutUs.descriptionVideo
    })
  }
  openEditFormIntro(intro : Intro){
    this.editFormIntro.setValue({
      intro_id : intro.id,
      name : intro.name,
      intro_des : intro.description,
    })
  }
  async getListIntro(){
    let result = await this.introService.getListintro(null);
    this.intro = result.data;
  }
  async updateIntro(){
    if(confirm('Are you sure to edit ?')){
      let id = this.editFormIntro.get('intro_id').value
      let result = await this.introService.updateIntro(Number(id),
        new IntroRequest(
          this.editFormIntro.get('name').value,
          this.editFormIntro.get('intro_des').value
        )
      );
      if(result.type === "ERROR"){
        this.authService.showToastr(2,"Update Fail","Something Wrong :<")
      }else{
        this.authService.showToastr(1,"This News has been updated","Update Success")
        $('#editEmployeeModal2').modal('hide');
      }
      this.getListIntro();
    }
  }

  toNewTab(url: string) {
    window.open(url, "_blank")
  }
}
