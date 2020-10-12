import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { MainbannerService } from 'src/app/public/common/services/mainbanner.service';
import { MainBanner } from 'src/app/shared/models/mainbanner.model';
declare var $ : any;

@Component({
  selector: 'app-mainbanneradmin',
  templateUrl: './mainbanneradmin.component.html',
  styleUrls: ['./mainbanneradmin.component.scss']
})
export class MainbanneradminComponent implements OnInit {

  ListMainBanner: MainBanner[] = [];

  editBannerForm: FormGroup;
  addBannerForm: FormGroup;
  mainBanner : MainBanner;

  file : File;
  already = false;

  currentPage: number = 0;
  totalPage: number = 0;
  totalRecord: number = 0;
  totalRecordCurrentPage: number;

  listShowPage: number[] = [];

  constructor(private mainBannerService: MainbannerService, private build : FormBuilder) { }

  ngOnInit(): void {
    this.getAllBanner();
    this.editBannerForm = this.build.group({
      id : ['', Validators.required],
      name : ['', Validators.required],
      url : ['', Validators.required],
      image : ['', Validators.required]
    });

    this.addBannerForm = this.build.group({
      name : ['', Validators.required],
      url : ['', Validators.required],
      image : ['', Validators.required]
    })
  }

  async getAllBanner(): Promise<void> {
    let result = await this.mainBannerService.getPagingBanner
    ( null,
      this.currentPage
    );
    this.ListMainBanner = result.data.content;   
    this.totalRecord = result.data.totalElements;  
    this.totalPage = result.data.totalPages;
  }

  onSelectedFile(event){
    this.file = event.target.files[0];
    if(this.file){
      this.already = true;
    }
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
    this.getAllBanner();
  }

  getDataFromPreviousPage(){
    this.currentPage = this.currentPage - 1;
    if (this.currentPage < 0){
      this.currentPage = 0;
    }
    this.getAllBanner();
  }

  getDataFromNextPage(){
    this.currentPage = this.currentPage + 1;
    if (this.currentPage > this.totalPage - 1){
      this.currentPage = this.totalPage - 1;
    }
    this.getAllBanner();
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

  editBanner(){
    const idSubmit = this.editBannerForm.get('id').value;
    const formSubmit = new MainBanner(
      this.editBannerForm.get('name').value,
      this.editBannerForm.get('url').value,
      this.editBannerForm.get('image').value
    )
    const formData = new FormData();
    formData.append("banner", JSON.stringify(formSubmit));
    if(this.already){
      formData.append("image", this.file);
      this.mainBannerService.editBannerHaveImage(formData, idSubmit).subscribe();
      this.already = false;
    }else{
      formData.delete("image");
      this.mainBannerService.editBanner(formData, idSubmit).subscribe();
    }

    $('#editBannerModal').modal('hide');
    this.getAllBanner();
  }

  fileExists = false; 

  addBanner(){

    const formSubmit = new MainBanner(
      this.addBannerForm.get('name').value,
      this.addBannerForm.get('url').value,
      this.addBannerForm.get('image').value
    )
   
    const formData = new FormData();
    formData.append("banner", JSON.stringify(formSubmit));
    formData.append("image", this.file);
    this.mainBannerService.addBanner(formData).subscribe(
      data => {
        $('#addBannerModal').modal('hide');
        this.getAllBanner();
      },err =>{
        this.fileExists = true;
      } 
    );
  }

  closeAddForm(){
    this.fileExists = false;
  }

  openEditForm(mainBanner : MainBanner){
    this.editBannerForm.setValue({
      id : mainBanner.id,
      name : mainBanner.name,
      url : mainBanner.url,
      image : mainBanner.image
    })
  }

  async deleteBanner(id : number){
    if(confirm('Are you sure to remove ?')){
      this.mainBannerService.deleteBanner(id).subscribe();
      this.getAllBanner();
    }
  }

  toNewPage(url : string) {
    window.open(url, "_blank")
  }

}
