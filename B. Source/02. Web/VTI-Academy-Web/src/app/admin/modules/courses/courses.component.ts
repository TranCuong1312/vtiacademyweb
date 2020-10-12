import { CourseOutcomeEditComponent } from './course-outcome-edit/course-outcome-edit.component';
import { AuthService } from 'src/app/shared/services/auth.service';
import { CourseService } from 'src/app/public/common/services/course.service';
import { Course } from 'src/app/shared/models/course.model';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SubCourse } from 'src/app/shared/models/subcourse.model';
import { SubcourseService } from 'src/app/public/common/services/subcourse.service';
import { data } from 'jquery';
import { AngularEditorConfig } from '@kolkov/angular-editor';
declare var $:any;

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {
  listCourse: Course[] = [];
  currentPage: number = 0;
  totalPage: number = 0;
  totalRecord: number = 0;
  totalRecordCurrentPage: number;
  listShowPage: number[] = [];

  course: Course; 
  editCourseForm: FormGroup;
  addCourseForm: FormGroup; 
  listSubCourses: SubCourse[] = [];
  listSubCoursesId: string[] = [];
  listAllSubCourses: SubCourse[] = [];
  num: any;

  existSubcourse: SubCourse[] = [];

  introData : string;
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

  @ViewChild(CourseOutcomeEditComponent ) child : CourseOutcomeEditComponent ;

  constructor(
    private courseService: CourseService,
    private subCourseService: SubcourseService,
    private authService : AuthService,
    private build : FormBuilder
  ) { }

  receiveDataFromChild(data) {
    this.getListCourse();
    this.child.course = this.course;
  }

  ngOnInit(): void {
    this.getListCourse();    
    this.getAllSubCourses();
    this.editCourseForm = this.build.group({
      id : ['', Validators.required],
      name : ['', Validators.required],
      img : ['', Validators.required],
      intro : ['', Validators.required],
      curriculum : ['', Validators.required],
      subcourses : new FormArray([])
    });
    this.addCourseForm = this.build.group({
      name : ['', Validators.required],
      img : ['', Validators.required],
      intro : ['', Validators.required],
      curriculum : ['', Validators.required],
      createDate : [Date.now()],
      subcourse : new FormArray([])
    });
  } 

  onCheckChangeAdd(event) {
    const formArray: FormArray = this.addCourseForm.get('subcourse') as FormArray;
    /* Selected */
    if(event.target.checked){
      // Add a new control in the arrayForm
      formArray.push(new FormControl(event.target.value));
    }
    /* unselected */
    else{
      // find the unselected element
      let i: number = 0;
      formArray.controls.forEach((ctrl: FormControl) => {
        if(ctrl.value == event.target.value) {
          // Remove the unselected element from the arrayForm
          formArray.removeAt(i);
          return;
        }
        i++;
      });
    }
  }

  onCheckChangeEdit(event) {
    const formArray: FormArray = this.editCourseForm.get('subcourses') as FormArray;
    /* Selected */
    if(event.target.checked){
      // Add a new control in the arrayForm
      formArray.push(new FormControl(event.target.value));
    }
    /* unselected */
    else{
      // find the unselected element
      let i: number = 0;
      formArray.controls.forEach((ctrl: FormControl) => {
        if(ctrl.value == event.target.value) {
          // Remove the unselected element from the arrayForm
          formArray.removeAt(i);
          return;
        }
        i++;
      });
    }
  }

  // isCheck(id: number) {
  //   let listId = [];
  //   this.listSubCourses.forEach(id => {
  //     listId.push(id.id);
  //   })
  //   return listId.includes(id);
  // }

  async getListCourse(): Promise<void>{
    let result = await this.courseService.getListCoursesPaging(null, this.currentPage);
    this.listCourse = result.data.contents;
    this.totalPage = result.data.metadata.totalPage;
    this.totalRecord = result.data.metadata.total;
  }

  async getListSubCourse(id: number): Promise<void>{
    let result = await this.courseService.getCoursesAdmin(id);
    this.listSubCourses = result.data;
  }

  async getAllSubCourses(): Promise<void>{
    let result = await this.subCourseService.getAllSubcourses();
    this.listAllSubCourses = result.data;
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
    this.getListCourse();
  }

  getDataFromPreviousPage(){
    this.currentPage = this.currentPage - 1;
    if (this.currentPage < 0){
      this.currentPage = 0;
    }
    this.getListCourse();
  }

  getDataFromNextPage(){
    this.currentPage = this.currentPage + 1;
    if (this.currentPage > this.totalPage - 1){
      this.currentPage = this.totalPage - 1;
    }
    this.getListCourse();
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

  deleteCourse(id: number) {
    if (confirm('Are you sure to remove ?')) {
      this.courseService.deletePost(id).subscribe(
        (data) => {
          this.authService.showToastr(1, 'This Course has been remove', 'Delete Success');
        },
        (err) => {
          this.authService.showToastr(2, 'Delete Fail', 'Something Wrong :<');
        }
      );
    }
  }

  onShowSubCourseClick(id: number){
    this.getListSubCourse(id);
  }

  
  formEdit : Course;
  file1: File
  haveImage : boolean = false;
  formData2 = new FormData();
  onSelectedFileEdit(event){
    this.file1 = event.target.files[0];
    if(this.file1){
      this.haveImage = true;
    }
  }

  async submitEditCourse() {
    let listSubCoursesId = this.editCourseForm.get('subcourses').value;
    this.num = this.editCourseForm.get('id').value;
      listSubCoursesId.forEach(element => {
        // this.courseService.addSubCoursesIntoCourse(this.num, Number(element)).subscribe();
        this.add(Number(element));
      });
    this.formEdit = new Course (
      this.editCourseForm.get('name').value,
      this.editCourseForm.get('img').value,
      this.introData,
      this.editCourseForm.get('curriculum').value
    );

    this.formData2.append('course', JSON.stringify(this.formEdit));
    this.formData2.append('image', this.file1);

    if(confirm('Are you sure to edit ?')){
        let result;
        if(this.haveImage == true) {
          result = await this.courseService.editCourseHaveImage(this.num,this.formData2);
        }else {
          result = await this.courseService.editCourseNoImage(this.num,this.formData2);
        }

        this.courseService.deleteCourseSyllabuse(this.num).then(
          (res) => {
            listSubCoursesId.forEach(element => {
              // this.courseService.addSubCoursesIntoCourse(this.num, Number(element)).subscribe();
              this.add(Number(element));
            });
          }
          )
        .catch(
          (error) => {
        });
        
      if(result.type === "ERROR"){
        this.authService.showToastr(2,"Update Fail","Something Wrong :<")
      }else {
        this.authService.showToastr(1,"This News has been updated","Update Success");
        $('#editEmployeeModal').modal('hide');
      };

    };
    window.location.reload();
  }

  onOpenEditCourse(course : Course) { 
    this.editCourseForm.setValue({
      id : course.id,
      name : course.name,
      img : course.img,
      intro : course.intro,
      curriculum : course.curriculum,
      subcourses : null
    })
    this.getListSubCourse(course.id);
    
  }
////////////////////////////////////////////////

  file2: File
  onSelectedFileAdd(event){
    this.file2 = event.target.files[0];
  }

  formSubmit : Course;
  formData = new FormData();
  async submitAddCourse(){
    // get list subcoursesId which user choose
    this.listSubCoursesId = this.addCourseForm.get('subcourse').value;  
    this.formSubmit = new Course (
      this.addCourseForm.get('name').value,
      this.addCourseForm.get('img').value,
      this.introData,
      this.addCourseForm.get('curriculum').value
    ); 

    console.log(JSON.stringify(this.formSubmit));
    this.formData.append("course", JSON.stringify(this.formSubmit));
    this.formData.append("image", this.file2);  
    // add course and get ID of course 
   await this.addCourses(this.formData);
  }

  async addCourses(formData : FormData){
    if(confirm('Are you sure to add?')) {
     await this.courseService.addCourses(formData).subscribe(data =>{

     },err => {
        $('#addEmployeeModal').modal('hide');
        this.addCourseForm.reset();  
        this.getMaxIdCourse();
     });    
    }
  }

    
  async  getMaxIdCourse(){
    await this.courseService.getMaxId().subscribe(data => {    
      this.num = data;    
      this.addCourseSyllabuses();
    });
  }

  addCourseSyllabuses(){
    this.listSubCoursesId.forEach(element => {
      this.add(Number(element));
    });
  }

  async add(e: number) {
    await this.courseService.addSubCoursesIntoCourse(this.num, Number(e)).subscribe();
  }

  goToLink(url: string){
    window.open(url, "_blank");
  }

  onChange(event) {
    this.introData = event;
    console.log(this.introData);
  }
}
