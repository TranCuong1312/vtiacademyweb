import { AuthService } from './../../../shared/services/auth.service';
import { NewsRequest } from '../../common/models/news-request.model';
import { NewsService } from '../../../public/common/services/news.service';
import { News } from './../../../shared/models/news.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { AngularEditorConfig } from '@kolkov/angular-editor';
declare var $ : any;

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {
  currentPage: number = 0;
  totalPage: number;
  totalRecord: number;
  totalRecordCurrentPage: number;

  listShowPage: number[] = [];

  addNewForm : FormGroup;
  editForm : FormGroup;

  contentData: string;

  listNews: News[] = [];


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

  constructor(
    private newsService: NewsService,
    private build : FormBuilder,
    private datePipe : DatePipe,
  ) {}

  ngOnInit(): void {
    this.getListNews();

    this.addNewForm = this.build.group({
      title : ['', Validators.required],
      content : ['', Validators.required],
      author : ['', Validators.required],
      image : ['', Validators.required],
      shortDes : ['', Validators.required],
    });

    this.editForm = this.build.group({
      id : [''],
      title : ['', Validators.required],
      content : ['', Validators.required],
      author : ['', Validators.required],
      createDate : ['', Validators.required],
      image : ['', Validators.required],
      shortDes : ['', Validators.required],
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
    this.getListNews();
  }

  getDataFromPreviousPage(){
    this.currentPage = this.currentPage - 1;
    if (this.currentPage < 0){
      this.currentPage = 0;
    }
    this.getListNews();
  }

  getDataFromNextPage(){
    this.currentPage = this.currentPage + 1;
    if (this.currentPage > this.totalPage - 1){
      this.currentPage = this.totalPage - 1;
    }
    this.getListNews();
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

  async getListNews(): Promise<void> {
    let result = await this.newsService.getListNews2Paging(null, this.currentPage);
    this.listNews = result.data.contents;
    this.totalPage = result.data.metadata.totalPage;
    this.totalRecord = result.data.metadata.total;
  }

  file: any;

  onSelectedFile(event){
    this.file = event.target.files[0];
    if(this.file){
      this.already = true;
    }
  }

  fileExists= false;

  async addNews(){
    const formSubmit = new NewsRequest(this.title, this.contentData, this.author, this.createDate, this.image, this.shortDes);
    const formData = new FormData();
    formData.append('image', this.file);
    formData.append('new', JSON.stringify(formSubmit));
    this.newsService.addNews(formData).subscribe(data => {
      $('#addEmployeeModal').modal('hide');
    }, 
    err=>{
      this.fileExists = true;
    });
    this.getListNews();
  }

  closeAddForm(){
    this.fileExists = false;
  }

  async deleteNews(id : number){
    if(confirm('Are you sure to remove ?')){
      let result = await this.newsService.deleteNews(id);
      this.getListNews();
    }
  }

  openEditForm(news : News){
    this.editForm.setValue({
      id : news.id,
      title : news.title,
      content : news.content,
      author : news.author,
      createDate : this.createDateUpdate(news),
      image : news.image,
      shortDes : news.shortDes,
    })
  }

  already = false;

  async editNews(){
    if(confirm('Are you sure to edit ?')){
      const formSubmit = new NewsRequest(
        this.editForm.get('title').value,
        this.contentData,
        this.editForm.get('author').value,
        this.editForm.get('createDate').value,
        this.editForm.get('image').value,
        this.editForm.get('shortDes').value,
      )
      const idSubmit = this.editForm.get('id').value;
      const formData = new FormData();
      if(this.already == true){
        formData.append('image', this.file);
        formData.append('new', JSON.stringify(formSubmit));
        this.newsService.editNewHaveImage(formData, idSubmit).subscribe();
        this.already = false;
      }else{
        formData.delete('image');
        formData.append('new', JSON.stringify(formSubmit));
        this.newsService.editNew(formData, idSubmit).subscribe();
      }
      $('#editEmployeeModal').modal('hide');
      this.getListNews();
      
    }
  }

  get title(){
    return this.addNewForm.get('title').value;
  }
  get content(){
    return this.addNewForm.get('content').value;
  }
  get author(){
    return this.addNewForm.get('author').value;
  }
  get createDate(){
    return this.datePipe.transform(Date.now(),'yyyy-MM-dd');
  }
  createDateUpdate(news : News){
    return this.datePipe.transform(news.createDate,'yyyy-MM-dd');
  }
  get image(){
    return this.addNewForm.get('image').value;
  }
  get shortDes(){
    return this.addNewForm.get('shortDes').value;
  }

  onChange(event) {
    this.contentData = event;
  }

  
}
