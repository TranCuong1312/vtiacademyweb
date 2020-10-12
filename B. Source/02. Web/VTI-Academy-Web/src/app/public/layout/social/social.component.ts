import { ContactService } from './../../common/services/contact.service';
import { Contact } from './../../../shared/models/contact.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-social',
  templateUrl: './social.component.html',
  styleUrls: ['./social.component.scss']
})
export class SocialComponent implements OnInit {
  contact: Contact;
  showScholarshipForm = false;
  constructor(private contactService : ContactService) { }

  ngOnInit(): void {
    this.getContact();
  }
  async getContact(): Promise<void>{
    let result = await this.contactService.getContact(null);
    this.contact = result.data[0];
  }
  ScholarshipForm(){
    this.showScholarshipForm = !this.showScholarshipForm;
}
}
