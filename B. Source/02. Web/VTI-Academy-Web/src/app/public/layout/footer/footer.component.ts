import { ContactService } from './../../common/services/contact.service';
import { Contact } from './../../../shared/models/contact.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})

export class FooterComponent implements OnInit {
  contact: Contact;

  constructor(private contactService : ContactService) { }

  ngOnInit(): void {
    this.getContact();
  }

  async getContact(): Promise<void>{
    let result = await this.contactService.getContact(null);
    this.contact = result.data[0];
  }
  
}
