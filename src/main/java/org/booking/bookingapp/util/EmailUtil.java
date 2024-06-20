package org.booking.bookingapp.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

  @Autowired
  private JavaMailSender javaMailSender;

  public void sendOtpEmail(String email, String otp) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    mimeMessageHelper.setTo(email);
    mimeMessageHelper.setSubject("Verify OTP");
    mimeMessageHelper.setText("""
        <div>
          Your OTP for your email: %s is <b>%s</b>
        </div>
        """.formatted(email, otp), true);

    javaMailSender.send(mimeMessage);
  }

  public void sendGeneratePassword(String email, String password) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    mimeMessageHelper.setTo(email);
    mimeMessageHelper.setSubject("Your password has been changed");
    mimeMessageHelper.setText("""
        <div>
          Your new password for your email: %s is <b>%s</b>
        </div>
        """.formatted(email, password), true);

    javaMailSender.send(mimeMessage);
  }
}
