import { AuthService } from './../../../shared/services/auth.service';
import { Mentor } from './../../../shared/models/mentor.model';
import { MentorRequest } from '../../common/models/mentor-request.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MentorsService } from 'src/app/public/common/services/mentors.service';
import { Component, OnInit } from '@angular/core';
declare var $: any;

@Component({
  selector: 'app-mentor',
  templateUrl: './mentor.component.html',
  styleUrls: ['./mentor.component.scss'],
})

export class MentorComponent implements OnInit {
  listMentor: Mentor[] = [];
  currentPage: number = 0;
  totalPage: number = 0;
  totalRecord: number = 0;
  totalRecordCurrentPage: number;

  listShowPage: number[] = [];

  mentor: Mentor;
  addMentorForm: FormGroup;
  editMentorForm: FormGroup;

  constructor(
    private mentorsService: MentorsService,
    private authService : AuthService,
    private build : FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.getListMentor();  

    this.addMentorForm = this.build.group({
      name : ['', Validators.required],
      position: ['', Validators.required],
      content: ['', Validators.required],
      img: ['', Validators.required]
    });

    this.editMentorForm = this.build.group({
      id: ['', Validators.required],
      name: ['',  Validators.required],
      position: ['',  Validators.required],
      content: ['',  Validators.required],
      img: ['',  Validators.required]
    });
  }

  file: any;
  already = false;

  onSelectedFile(event) {
    this.file = event.target.files[0];
    if(this.file) {
      this.already = true;
    }
  }

  async getListMentor(): Promise<void> {
    let result = await this.mentorsService.getListMentorPaging(
      null,
      this.currentPage
    );
    this.listMentor = result.data.contents;
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
    this.getListMentor();
  }

  getDataFromPreviousPage(){
    this.currentPage = this.currentPage - 1;
    if (this.currentPage < 0){
      this.currentPage = 0;
    }
    this.getListMentor();
  }

  getDataFromNextPage(){
    this.currentPage = this.currentPage + 1;
    if (this.currentPage > this.totalPage - 1){
      this.currentPage = this.totalPage - 1;
    }
    this.getListMentor();
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

  async deleteMentor(id : number){
    if(confirm('Are you sure to remove ?')){
      let result = await this.mentorsService.deleteMentor(id);
      this.getListMentor();
    }
  }

  openEditForm(mentor: Mentor){
    this.editMentorForm.setValue({
      id: mentor.id,
      name: mentor.name,
      position: mentor.position,
      content: mentor.content,
      img: mentor.img
    })
  }

  fileExists = false;

  async addNewMentor() {
    const formSubmit = new MentorRequest(this.name, this.position, this.content, this.img);
    const formData = new FormData();
    formData.append('image', this.file);
    formData.append('mentor', JSON.stringify(formSubmit));
    this.mentorsService.addNewMentors(formData).subscribe(data => {
        $('#addEmployeeModal').modal('hide');
        this.getListMentor();
    },
    err => {
      this.fileExists = true;
    });
  }

  closeAddForm(){
    this.fileExists = false;
  }

  async editMentor() {
    if(confirm('Are you sure to edit ?')){
      const formSubmit = new MentorRequest(
        this.editMentorForm.get('name').value,
        this.editMentorForm.get('position').value,
        this.editMentorForm.get('content').value,
        this.editMentorForm.get('img').value
      )

      const idSubmit = this.editMentorForm.get('id').value;

      const formData = new FormData();

      if(this.already == true){
        formData.append('image', this.file);
        formData.append('mentor', JSON.stringify(formSubmit));
        this.mentorsService.editMentorHaveImage(formData, idSubmit).subscribe();
        this.already = false;
      } else{
        formData.delete('image');
        formData.append('mentor', JSON.stringify(formSubmit));
        this.mentorsService.editMentor(formData, idSubmit).subscribe();
      }
      $('#editEmployeeModal').modal('hide');
      this.getListMentor();
    }
  }

  
  get name() {
    return this.addMentorForm.get('name').value;
  }

  get position(){
    return this.addMentorForm.get('position').value;
  }

  get content(){
    return this.addMentorForm.get('content').value;
  }

  get img(){
    return this.addMentorForm.get('img').value;
  }

}
