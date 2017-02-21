import com.ny.mail.config.MailConfig;
import com.ny.mail.email.EmailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MailConfig.class})
public class SendEmail {

    @Autowired
    private EmailSender emailSender;

    @Test
    public void sendEmail() {
        emailSender.sendSimpleEmail("2220822589@qq.com","Hello World.","Hello,Hello papa.");
    }

    @Test
    public void sendEmailWithAttachment() {
        try {
            emailSender.sendEmailWithAttachment("2220822589@qq.com","Hello World.","Hello,Hello papa.",new ClassPathResource("/coupon.jpg"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendEmailWithHtml() {
        try {
            emailSender.sendEmailWithHtml("2220822589@qq.com","Hello World.","<html><body><img src=\"cid:logo\" alt=\"Logo\"><h1>Hello hello</h1></body></html>",new ClassPathResource("/coupon.jpg"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
