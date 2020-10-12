import { DatePipe } from '@angular/common';
import { AuthService } from './../../../shared/services/auth.service';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { SubcourseService } from './../../../public/common/services/subcourse.service';
import { SubCourse } from 'src/app/shared/models/subcourse.model';
import { Component, OnInit } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';

declare var $: any;

@Component({
  selector: 'app-subcourse',
  templateUrl: './subcourse.component.html',
  styleUrls: ['./subcourse.component.scss']
})
export class SubcourseComponent implements OnInit {
  currentPage: number = 0;
  totalPage: number = 0;
  totalRecord: number = 0;
  totalRecordCurrentPage: number;

  listShowPage: number[] = [];

  subcourses: SubCourse[] = [];
  addSubCourseForm: FormGroup;
  updateSubCourseForm: FormGroup;
  subCourse: SubCourse;
  id: number;

  contentData : string;
  editorConfig: AngularEditorConfig = {
    editable: true,
    spellcheck: true,
    minHeight: '5rem',
    maxHeight: '15rem',
    placeholder: '',
    translate: 'no',
    sanitize: false,
    // toolbarPosition: 'top',
    outline: true,
    defaultFontName: 'Arial',
    defaultFontSize: '5',
    // showToolbar: false,
    defaultParagraphSeparator: 'p',
    customClasses: [
      {
        name: 'quote',
        class: 'quote',
      },
      {
        name: 'redText',
        class: 'redText'
      },
      {
        name: 'titleText',
        class: 'titleText',
        tag: 'h1',
      },
    ]
  };


  constructor(private subcoureService: SubcourseService, private fb: FormBuilder, private authService: AuthService, private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.getSubcourses();

    this.addSubCourseForm = this.fb.group({
      name: ['', Validators.required],
      content: ['', Validators.required]
    });

    this.updateSubCourseForm = this.fb.group({
      id: [''],
      name: ['', Validators.required],
      content: ['', Validators.required],
      note: ['', Validators.required],
      isActive: ['']
    })

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
    this.getSubcourses();
  }

  getDataFromPreviousPage(){
    this.currentPage = this.currentPage - 1;
    if (this.currentPage < 0){
      this.currentPage = 0;
    }
    this.getSubcourses();
  }

  getDataFromNextPage(){
    this.currentPage = this.currentPage + 1;
    if (this.currentPage > this.totalPage - 1){
      this.currentPage = this.totalPage - 1;
    }
    this.getSubcourses();
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

  get name() { return this.addSubCourseForm.get('name') }

  get content() { return this.addSubCourseForm.get('content') }

  get note() { return this.updateSubCourseForm.get('note') }

  get active() { return this.updateSubCourseForm.get('active') }



  async getSubcourses(): Promise<void> {
    let result = await this.subcoureService.getSubcoursesPaging(null, this.currentPage);
    this.subcourses = result.data.contents;
    this.totalPage = result.data.metadata.totalPage;
    this.totalRecord = result.data.metadata.total;
  }

  async addSubCourse(): Promise<void> {
    console.log(this.addSubCourseForm.value);
    this.subcoureService.addSubCourse(this.addSubCourseForm.value)
      .then(
        res => {
          this.authService.showToastr(1, 'Add success', '');
        }
      ).then(data => {
        $('#addEmployeeModal').modal('hide');
        this.getSubcourses();
      })
  }

  async deleteSubCourse(id: number): Promise<void> {
    await this.subcoureService.deletePost(id)
      .then(
        res => {
          this.authService.showToastr(1, 'Delete success', '');
        }
      )
      .catch(
        error => {
          this.authService.showToastr(2, 'Delete fail', '');
        }
      );
      this.getSubcourses();
  }

  async updateSubCourse(id: number) {
    await this.subcoureService.updatePost(id, this.updateSubCourseForm.value)
      .then(
        res => {
          this.authService.showToastr(1, 'Update success', '');
        }
      ).then(data => {
        $('#editEmployeeModal').modal('hide');
        this.getSubcourses();
      });
    this.getSubcourses();
  }

  onOpenUpdateForm(subCourse: SubCourse) {
    this.id = subCourse.id;
    this.updateSubCourseForm.setValue({
      id: subCourse.id,
      name: subCourse.name,
      content: subCourse.content,
      note: subCourse.note,
      isActive: null
    })
  }

  onAddSubCourseSubmit() {
    this.addSubCourse();
  }

  onDeleteSubCourseClick(id: number) {
    this.deleteSubCourse(id);
  }

  onUpdateSubCourseSubmit() {
    this.updateSubCourse(this.id);
  }

  onChange(event) {
    this.contentData = event;
    console.log(this.contentData);
  }
}
