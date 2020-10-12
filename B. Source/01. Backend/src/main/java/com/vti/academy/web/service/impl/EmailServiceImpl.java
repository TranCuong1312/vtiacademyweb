package com.vti.academy.web.service.impl;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.vti.academy.web.model.User;
import com.vti.academy.web.model.dto.FormRegisterDTO;
import com.vti.academy.web.service.EmailService;

@Service
@Component
@EnableAsync
public class EmailServiceImpl implements EmailService {
	// register
		@Value("${mail.registered.email}")
		private String emailRegisteredFrom;
		
		// admin
		@Value("${mail.admin.email}")
		private String emailAdminForm;
		
		// reset
		@Value("${mail.reseted.email}")
		private String emailResetedFrom;

		@Autowired
		private JavaMailSenderImpl javaMailSender;

		@Autowired
		private MessageSource messageSource;

//		@Async
//		@Override
//		public void sendRegisteredEmailType1(FormRegisterDTO formRegisterDTO) {
//
//			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//			MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, "utf-8");
//			try {
//				mailMessage.setFrom(emailRegisteredFrom);
//				mailMessage.setTo(formRegisterDTO.getEmail());
//				mailMessage.setSubject(messageSource.getMessage("mail.registered.subject", null, Locale.CANADA));
//				mailMessage
//						.setText(messageSource.getMessage("mail.greeting", null, Locale.CANADA) + formRegisterDTO.getName()
//								 + messageSource.getMessage("mail.registered.content", null, Locale.CANADA),true);
//				mimeMessage.saveChanges();
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}
//			javaMailSender.send(mimeMessage);
//		}
		
		
		@Async
		@Override
		public void sendNoticeEmailType1(FormRegisterDTO formRegisterDTO) {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, "utf-8");
			try {
				mailMessage.setFrom(emailRegisteredFrom);
				mailMessage.setTo(emailAdminForm);
				mailMessage.setSubject(messageSource.getMessage("mail.registered.subject", null, Locale.CANADA));
				mailMessage
						.setText(
								messageSource.getMessage("mail.notice.subject", null, Locale.CANADA)
								+ messageSource.getMessage("mail.notice.content", null, Locale.CANADA) 
								+ messageSource.getMessage("mail.notice.name.content", null, Locale.CANADA) + formRegisterDTO.getName()
								+ messageSource.getMessage("mail.notice.phone.content", null, Locale.CANADA) + formRegisterDTO.getPhone()
								+ messageSource.getMessage("mail.notice.mail.content", null, Locale.CANADA) + formRegisterDTO.getEmail()
								,true);
				mimeMessage.saveChanges();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			javaMailSender.send(mimeMessage);
		}
		
//		/* 
//		* @see com.vti.academy.web.service.EmailService#sendRegisterEmailType3(com.vti.academy.web.model.dto.FormRegisterDTO)
//		*/
//		@Override
//		public void sendRegisteredEmailType3(FormRegisterDTO formRegisterDTO) {
//			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//			MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, "utf-8");
//			try {
//				mailMessage.setFrom(emailRegisteredFrom);
//				mailMessage.setTo(formRegisterDTO.getEmail());
//				mailMessage.setSubject(messageSource.getMessage("mail.registered.subject2", null, Locale.CANADA));
//				mailMessage
//						.setText(messageSource.getMessage("mail.greeting", null, Locale.CANADA) + formRegisterDTO.getName()
//								 + messageSource.getMessage("mail.registered.content2", null, Locale.CANADA),true);
//				mimeMessage.saveChanges();
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}
//			javaMailSender.send(mimeMessage);
//		}


		/* 
		* @see com.vti.academy.web.service.EmailService#sendNoticeEmailType3(com.vti.academy.web.model.dto.FormRegisterDTO)
		*/
		@Override
		public void sendNoticeEmailType3(FormRegisterDTO formRegisterDTO) {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, "utf-8");
			try {
				mailMessage.setFrom(emailRegisteredFrom);
				mailMessage.setTo(emailAdminForm);
				mailMessage.setSubject(messageSource.getMessage("mail.registered.subject2", null, Locale.CANADA));
				mailMessage
						.setText(
								messageSource.getMessage("mail.notice.subject2", null, Locale.CANADA)
								+ messageSource.getMessage("mail.notice.content2", null, Locale.CANADA) 
								+ messageSource.getMessage("mail.notice.name.content2", null, Locale.CANADA) + formRegisterDTO.getName()
								+ messageSource.getMessage("mail.notice.phone.content2", null, Locale.CANADA) + formRegisterDTO.getPhone()
								+ messageSource.getMessage("mail.notice.mail.content2", null, Locale.CANADA) + formRegisterDTO.getEmail()
								,true);
				mimeMessage.saveChanges();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			javaMailSender.send(mimeMessage);
		}
		

		@Async
		@Override
		public void sendResetedPassword(User user, String passwordUpdated) {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, "utf-8");

			try {
				mailMessage.setFrom(emailResetedFrom);
				mailMessage.setTo(user.getEmail());
				mailMessage.setSubject(messageSource.getMessage("mail.reseted.subject", null, Locale.CANADA));
				mailMessage.setText(messageSource.getMessage("mail.greeting", null, Locale.CANADA) + user.getUsername()
						+ messageSource.getMessage("mail.reseted.content.head", null, Locale.CANADA) + passwordUpdated
						+ messageSource.getMessage("mail.reseted.content.tail", null, Locale.CANADA), true);
				mimeMessage.saveChanges();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			javaMailSender.send(mimeMessage);

		}


}
