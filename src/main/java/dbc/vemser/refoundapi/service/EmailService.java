package dbc.vemser.refoundapi.service;


import dbc.vemser.refoundapi.entity.RefundEntity;
import dbc.vemser.refoundapi.enums.Status;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final freemarker.template.Configuration fmConfiguration;

    @Value("${spring.mail.username}")
    private String FROM;
    private final JavaMailSender emailSender;

//    public void sendSimpleMessage(String mensagemText, String email) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(email);
//        message.setSubject("TESTE");
//        message.setText(mensagemText);
//        emailSender.send(message);
//    }

//    public void sendWithAttachment() throws MessagingException {
//        MimeMessage message = emailSender.createMimeMessage();
//
//        MimeMessageHelper helper = new MimeMessageHelper(message,
//                true);
//
//        helper.setFrom(from);
//        helper.setTo(MAIL_TO);
//        helper.setSubject("TESTE");
//        helper.setText("Teste\n minha mensagem \n\nAtt,\nEu.");
//
//        File file1 = new File("imagem.jpg");
//
//        FileSystemResource file
//                = new FileSystemResource(file1);
//        helper.addAttachment(file1.getName(), file);
//
//        emailSender.send(message);
//    }

    public void sendEmail(String to, RefundEntity refundEntity) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(FROM);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(refundEntity.getStatus().getSubject());
            mimeMessageHelper.setText(getContentFromTemplate(refundEntity, refundEntity.getStatus().getName()), true);
            emailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public String getContentFromTemplate(RefundEntity refundEntity, String templateName) throws IOException, TemplateException {
        Map<String, Object> data = new HashMap<>();
        data.put("title", refundEntity.getTitle());
        data.put("value", String.format("%.2f", refundEntity.getValue()));

        fmConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        Template template = fmConfiguration.getTemplate(templateName+".ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
        return html;
    }
}
