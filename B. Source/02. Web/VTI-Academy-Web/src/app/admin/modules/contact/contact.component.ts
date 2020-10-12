import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ContactService } from 'src/app/public/common/services/contact.service';
import { Contact } from 'src/app/shared/models/contact.model';
import { AuthService } from 'src/app/shared/services/auth.service';
declare var $ : any;

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {
  contact: Contact[];
  contactForm: FormGroup;

  constructor(private contactService: ContactService, private build: FormBuilder, private authService: AuthService) { }

  ngOnInit(): void {
    this.getContact();    
    this.contactForm = this.build.group({
      id: [''],
      address: [''],
      address2: [''],
      phone: [''],
      phone2: [''],
      domain: [''],
      facebook: [''],
      email: [''],
      youtube: ['']
    })
  }

  async getContact(): Promise<void>{
    let result = await this.contactService.getContact(null);
    this.contact = result.data;
  }

  onOpenContactEditForm(value: Contact) {    
    this.contactForm.setValue({
      id : value.id,
      address : value.address,
      address2: value.address2,
      phone: value.phone,
      phone2: value.phone2,
      domain: value.domain,
      facebook: value.facebook,
      email: value.email,
      youtube: value.youtube
    })
  }

  async submitEditContact(value: Contact) {
    if(confirm('Are you sure to edit ?')){
      let result = await this.contactService.updateContact(value.id, value);
      if(result.type === "ERROR"){
        this.authService.showToastr(2,"Update Fail","Something Wrong :<")
      } else {
        this.authService.showToastr(1,"This Contact has been updated","Update Success")
        $('#editEmployeeModal').modal('hide');
      }
    };
    this.getContact();
  }
  
  toNewTab(url : string) {
    window.open(url, "_blank");
  }

}
