import { Component, OnInit } from '@angular/core';
import { ReviewService } from 'src/app/public/common/services/review.service';
import { AuthService } from 'src/app/shared/services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Review } from 'src/app/shared/models/review.model';
declare var $: any;

@Component({
  selector: 'app-review-mentor',
  templateUrl: './review-mentor.component.html',
  styleUrls: ['./review-mentor.component.scss']
})
export class ReviewMentorComponent implements OnInit {

  listReviewMentors: any[] = [];
  currentPage: number = 0;
  totalPage: number = 0;
  totalRecord: number = 0;
  totalRecordCurrentPage: number;

  listShowPage: number[] = [];

  addReviewForm: FormGroup;
  editReviewForm: FormGroup;
  review: Review;

  constructor(
    private reviewService: ReviewService,
    private authService: AuthService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getListReviewMentors();
    this.addReviewForm = this.formBuilder.group({
      image: ['', Validators.required], 
      content: ['', Validators.required],
      reviewerName: ['', Validators.required],
      office: ['', Validators.required]
    });
    this.editReviewForm = this.formBuilder.group({
      id: [''],
      image: ['', Validators.required], 
      content: ['', Validators.required],
      reviewerName: ['', Validators.required],
      office: ['', Validators.required]
    });
  }

  async getListReviewMentors(): Promise<void> {
    let result = await this.reviewService.getMentorReviewPaging(null, this.currentPage);
    this.listReviewMentors = result.data.contents;
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
    this.getListReviewMentors();
  }

  getDataFromPreviousPage(){
    this.currentPage = this.currentPage - 1;
    if (this.currentPage < 0){
      this.currentPage = 0;
    }
    this.getListReviewMentors();
  }

  getDataFromNextPage(){
    this.currentPage = this.currentPage + 1;
    if (this.currentPage > this.totalPage - 1){
      this.currentPage = this.totalPage - 1;
    }
    this.getListReviewMentors();
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

  openEditForm(curReview: Review) {
    this.editReviewForm.setValue({
      id: curReview.id,
      reviewerName: curReview.reviewerName,
      office: curReview.office,
      content: curReview.content,
      image: curReview.image
    })
  }

  file: any;
  already = false;

  onSelectedFile(event){
    this.file = event.target.files[0];
    if(this.file){
      this.already = true;
    }
  }

  async deleteReview(id: number) {
    if (confirm('Are you sure to remove ?')) {
      let result = await this.reviewService.deleteReview(id);
      if (result.type === 'ERROR') {
        this.authService.showToastr(2, "Delete Fail", "Something Wrong :<");
      } else {
        this.authService.showToastr(1, "This News has been remove", "Delete Success");
      }
      this.getListReviewMentors();
    }
  }

  formSubmit : Review;
  fileExists = false;
  async addReview() {
    this.formSubmit = new Review (
      this.addReviewForm.get('image').value,
      this.addReviewForm.get('content').value,
      this.addReviewForm.get('reviewerName').value,
      this.addReviewForm.get('office').value,
      'CHUYENGIA'
    ); 
    const formData = new FormData();
    formData.append("review", JSON.stringify(this.formSubmit));
    formData.append("image", this.file);
    let result = await this.reviewService.addReview(formData);
   
    if(result.code === 3){
      this.fileExists = true;
    }
    else{
      $('#addEmployeeModal').modal('hide');
      this.getListReviewMentors();
    }
  }

  closeAddForm(){
    this.fileExists = false;
  }

  formEdit : Review;
  async editReview() {
    if (confirm('Are you sure to edit ?')) {

      let id = this.editReviewForm.get('id').value;

      this.formEdit = new Review (
        this.editReviewForm.get('image').value,
        this.editReviewForm.get('content').value,
        this.editReviewForm.get('reviewerName').value,
        this.editReviewForm.get('office').value,
        'CHUYENGIA'
      ); 

       
    const formData = new FormData();
    formData.append("review", JSON.stringify(this.formEdit));
      let result : any;
      
      if(this.already){
        formData.append("image", this.file);
        result = await this.reviewService.editReviewHaveImage(Number(id), formData);
      }
      else{
        formData.delete("image");
        result = await this.reviewService.editReview(Number(id), formData);
      }
      
      if (result.type === "ERROR") {
        this.authService.showToastr(2, "Update Fail", "Something Wrong :<")
      } else {
        this.authService.showToastr(1, "This Review has been updated", "Update Success")
        $('#editEmployeeModal').modal('hide');
      }
    }
    this.getListReviewMentors();
  }

}
