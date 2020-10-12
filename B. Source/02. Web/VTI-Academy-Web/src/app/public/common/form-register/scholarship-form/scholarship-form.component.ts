import { AuthService } from './../../../../shared/services/auth.service';
import { RegisterFormService } from './../../services/register-form.service';
import { Component, OnInit, Input } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-scholarship-form',
  templateUrl: './scholarship-form.component.html',
  styleUrls: ['./scholarship-form.component.scss']
})
export class ScholarshipFormComponent implements OnInit {

  @Input('reviewInput') review: string;

  scholarForm: FormGroup;

  formClass: string = 'form-scholarship';
  closeClass: string = 'close';
  isSubmit: boolean = false;

  constructor(private fb: FormBuilder, private registerFormService: RegisterFormService, private authService: AuthService) { }

  ngOnInit(): void {
    this.scholarForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(4)]],
      phone: ['', [Validators.required, Validators.pattern('[0-9]{10,11}')]],
      email: ['', [Validators.required, Validators.email]]
    })
  }

  get name() { return this.scholarForm.get('name') }

  get phone() { return this.scholarForm.get('phone') }

  get email() { return this.scholarForm.get('email') }


  async createScholarshipForm(): Promise<void>{
    await this.registerFormService.createScholarshipForm(this.scholarForm.value)
    .catch(
      error =>{
        this.authService.showToastr(2,'Register fail!' ,'');
      }
    )
    .then(
      res =>{
        this.authService.showToastr(1,'Congratulations, you have successfully registered!' ,'');
      }
    );
  }

  onSubmit() {
    if (this.scholarForm.invalid) {
      this.isSubmit = true;
      this.authService.showToastr(2,'Register fail' ,'');
    }else{
      this.createScholarshipForm();
      this.scholarForm.reset();
    }
  }

  close() {
    this.formClass += ' ' + this.closeClass;
  }


}
