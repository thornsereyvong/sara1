package com.balancika.crm.services.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.balancika.crm.model.MailMessage;
import com.balancika.crm.services.MailService;

@Service
public class MailServiceImpl implements MailService{

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public boolean sendEmail(MailMessage mailMessage) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,"UTF-8");
			helper.setFrom("vchann@balancikacambodia.com");
			helper.setTo(mailMessage.getTo());
			helper.setSubject(mailMessage.getSubject());
			helper.setText(mailMessage.getMsg());
			/*helper.addAttachment(mailMessage.getAttachment().getOriginalFilename(), new InputStreamSource() {
				@Override
				public InputStream getInputStream() throws IOException {
					return mailMessage.getAttachment().getInputStream();
				}
			});*/
			mailSender.send(mimeMessage);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

}
