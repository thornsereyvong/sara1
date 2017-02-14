package com.balancika.crm.services.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
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
			helper.setFrom(mailMessage.getFrom());
			helper.setTo(mailMessage.getTo());
			helper.setSubject(mailMessage.getSubject());
			helper.setText(mailMessage.getMsg());
			helper.setBcc(mailMessage.getBcc());
			helper.setCc(mailMessage.getCc());
			helper.addAttachment(mailMessage.getAttachement().getOriginalFilename(), new InputStreamSource() {
				
				@Override
				public InputStream getInputStream() throws IOException {
					return mailMessage.getAttachement().getInputStream();
				}
			});
			mailSender.send(mimeMessage);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

}
