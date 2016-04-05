package utils;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("myMailSender")
public class MailSender {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	/**
	 * Gửi email cho một người từ hệ thống
	 * @param to email người nhận
	 * @param subject tiêu đề email
	 * @param body nội dung email
	 */
	public void send(String to, String subject, String body) {
		String from = "Luat's game <congluat27@gmail.com>";
		this.send(from, to, subject, body);
	}
	
	/**
	 * Gửi email cho một người
	 * @param from email người gửi
	 * @param to email người nhận
	 * @param subject tiêu đi email
	 * @param body nội dung email
	 */
	public void send(String from, String to, String subject, String body) {
		this.send(from, to, "", "", subject, body, "");
	}
	
	/**
	 * Gửi email cho nhiều người có đính kèm file
	 * @param from email người gửi
	 * @param to email người nhận
	 * @param ccs email những người cùng nhận công khai (cách dấu phẩy)
	 * @param bccs email những người cùng nhận bí mật (cách dấu phẩy)
	 * @param subject tiêu đề email
	 * @param body nội dung email
	 * @param attachments danh sách file đính kèm (cách dấu phẩy) 
	 */
	public void send(String from, String to, String ccs, String bccs, String subject, String body, String attachments) {
		try {
			MimeMessage mail = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");
			if(from.contains("<")){
				helper.setFrom(from);
				helper.setReplyTo(from);
			}
			else{
				helper.setFrom(from, from);
				helper.setReplyTo(from, from);
			}
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			
			if(ccs != null && ccs.trim().length() > 0){
				String[] emails = ccs.split("[\\s;,]+");
				for (String email : emails) {
					helper.addCc(email);
				}
			}
			
			if(bccs != null && bccs.trim().length() > 0){
				String[] emails = bccs.split("[\\s;,]+");
				for (String email : emails) {
					helper.addBcc(email);
				}
			}
			
			if(attachments != null && attachments.trim().length() > 0){
				String[] paths = attachments.split("[\\s;,]+");
				for (String path : paths) {
					File file = new File(path);
					helper.addAttachment(file.getName(), file);
				}
			}
			
			javaMailSender.send(mail);
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
