package com.balancika.crm.services;

import com.balancika.crm.model.MailMessage;

public interface MailService {
	boolean sendEmail(MailMessage mailMessage);
}
