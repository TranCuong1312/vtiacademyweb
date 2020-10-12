import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Enterprise } from 'src/app/shared/models/enterprise.model';
import { EnterpriseService } from 'src/app/public/common/services/enterprise.service';
import { AuthService } from 'src/app/shared/services/auth.service';
declare var $: any;

@Component({
  selector: 'app-enterprise-admin',
  templateUrl: './enterprise-admin.component.html',
  styleUrls: ['./enterprise-admin.component.scss']
})

export class EnterpriseAdminComponent implements OnInit {
  listEnterprise: Enterprise[] = [];
  currentPage: number = 0;
  totalPage: number = 0;
  totalRecord: number = 0;
  totalRecordCurrentPage: number;

  listShowPage: number[] = [];

  enterprise: Enterprise;
  addEnterpriseForm: FormGroup;
  editEnterpriseForm: FormGroup;

  constructor(
    private enterpriseService: EnterpriseService,
    private authService : AuthService,
    private build : FormBuilder
    ) {}

  ngOnInit(): void {
    this.getListEnterprise();

    this.addEnterpriseForm = this.build.group({
      name : ['', Validators.required],
      content: ['', Validators.required],
      icon: ['', Validators.required],
      image: ['', Validators.required]
    });

    this.editEnterpriseForm = this.build.group({
      id: [''],
      name: ['',  Validators.required],
      content: ['',  Validators.required],
      icon: ['',  Validators.required],
      image: ['',  Validators.required]
    });
  }

  async getListEnterprise(): Promise<void> {
    let result = await this.enterpriseService.getListEnterprisePaging(null, this.currentPage);
    this.listEnterprise = result.data.contents;
    this.totalPage = result.data.metadata.totalPage;
    this.totalRecord = result.data.metadata.total;
  }

  getTotalRecordCurrentPage(){
    if (this.totalPage-1 > this.currentPage){
      this.totalRecordCurrentPage = 3;
    } else if ((this.totalPage-1) == this.currentPage){
      this.totalRecordCurrentPage = this.totalRecord - ((this.currentPage)*3);
    }
    return this.totalRecordCurrentPage;
  }

  getDataFromCurrentPage(i: number) {
    this.currentPage = i;
    this.getListEnterprise();
  }

  getDataFromPreviousPage(){
    this.currentPage = this.currentPage - 1;
    if (this.currentPage < 0){
      this.currentPage = 0;
    }
    this.getListEnterprise();
  }

  getDataFromNextPage(){
    this.currentPage = this.currentPage + 1;
    if (this.currentPage > this.totalPage - 1){
      this.currentPage = this.totalPage - 1;
    }
    this.getListEnterprise();
  }

  getListShowPage() { 
    if(this.totalPage >= 3){
      if (this.currentPage == this.totalPage - 1){
        this.listShowPage = [this.currentPage -2, this.currentPage - 1, this.currentPage];
      } else if (this.currentPage == 0){
        this.listShowPage = [this.currentPage, this.currentPage + 1, this.currentPage + 2];
      } else {
        this.listShowPage = [this.currentPage - 1, this.currentPage, this.currentPage + 1];
      }     
    } else if (this.totalPage == 2){
      if (this.currentPage == this.totalPage - 1){
        this.listShowPage = [this.currentPage - 1, this.currentPage];
      } else if (this.currentPage == 0){
        this.listShowPage = [this.currentPage, this.currentPage + 1];
      } else {
        this.listShowPage = [this.currentPage - 1, this.currentPage];
      } 
    } else if (this.totalPage == 1){
      this.listShowPage = [this.currentPage];
    }
    return this.listShowPage;
    
  }

  async edit(enterprise: Enterprise): Promise<void> {
    this.editEnterpriseForm.setValue({
      id: enterprise.id,
      name: enterprise.name,
      content: enterprise.content,
      icon: enterprise.icon,
      image: enterprise.image
    })
  }

  file1: File;
  file2: File;
  already1 = false;
  already2 = false;
  onSelectedIcon(event){
    this.file1 = event.target.files[0];
    if(this.file1){
      this.already1 = true;
    }
  };
  onSelectedImage(event){
    this.file2 = event.target.files[0];
    if(this.file2){
      this.already2 = true;
    }
  };
  
  fileExists = false;

  async addNewEnterprise() {
      const formSubmit = new Enterprise(
        this.addEnterpriseForm.get('image').value,
        this.addEnterpriseForm.get('content').value,
        this.addEnterpriseForm.get('icon').value,
        this.addEnterpriseForm.get('name').value
        );

      console.log(JSON.stringify(formSubmit));
      
      const formData = new FormData();
      formData.append('icon', this.file1);
      formData.append('image', this.file2);
      formData.append('enterprise', JSON.stringify(formSubmit));
      this.enterpriseService.addNewEnterprise(formData).subscribe(data => {
        $('#addEmployeeModal').modal('hide');
        this.getListEnterprise();
      });
  }

  closeAddForm(){
    this.fileExists = false;
  }

  async editEnterprise() {
      const formData = new FormData;
      const idSubmit = this.editEnterpriseForm.get('id').value;
      const formSubmit = new Enterprise(
        this.editEnterpriseForm.get('image').value,
        this.editEnterpriseForm.get('content').value,
        this.editEnterpriseForm.get('icon').value,
        this.editEnterpriseForm.get('name').value
      );

      console.log(JSON.stringify(formSubmit));

      formData.append('enterprise', JSON.stringify(formSubmit));
      if(confirm('Are you sure to edit?')) {
        if(this.already1 == false && this.already2 == false) {
              formData.delete('image');
              formData.delete('icon');
              this.enterpriseService.editContentEnterprise(formData, idSubmit).subscribe();
        }else if (this.file1 == undefined) {
              formData.append('image', this.file2);
              this.enterpriseService.editImageEnterprise(formData, idSubmit).subscribe();
        }else if(this.file2 == undefined) {
              formData.append('icon', this.file1);
              this.enterpriseService.editIconEnterprise(formData, idSubmit).subscribe();
        }else {
              formData.append('icon', this.file1);
              formData.append('image', this.file2);
              this.enterpriseService.editEnterprise(formData, idSubmit).subscribe();
        }
        this.already1 = false;
        this.already2 = false;
        await this.getListEnterprise();
        $('#editEmployeeModal').modal('hide');
      } 
  }

  async delete(id: number): Promise<void> {
    if (confirm('Are you sure to remove ?')) {
      let result = await await this.enterpriseService.deleteEnterprise(id);
      if (result.type === 'ERROR') {
        this.authService.showToastr(2, 'Delete Fail', 'Something Wrong :<');
      } else {
        this.authService.showToastr(1,
          'This News has been remove',
          'Delete Success'
        );
      }
      this.getListEnterprise();
    }
  }
  
  get content(){
    return this.addEnterpriseForm.get('content').value;
  }
  get icon(){
    return this.addEnterpriseForm.get('icon').value;
  }
  get name(){
    return this.addEnterpriseForm.get('name').value;
  }
  get image(){
    return this.addEnterpriseForm.get('image').value;
  }

}
