package dbc.vemser.refoundapi.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@RequiredArgsConstructor
public class EmailService {

//    private final freemarker.template.Configuration fmConfiguration;
//
//    private static final String MAIL_TO = "flavio.sobrinho@dbccompany.com.br";
//
//    @Value("${spring.mail.username}")
//    private String from;
//    private final JavaMailSender emailSender;
//
//    public void sendSimpleMessage(String mensagemText, String email) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(email);
//        message.setSubject("TESTE");
//        message.setText(mensagemText);
//        emailSender.send(message);
//    }
//
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

//    public void sendEmail(PessoaDTO pessoaDTO, String assunto, String templateName) {
//        MimeMessage mimeMessage = emailSender.createMimeMessage();
//        try {
//
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//
//            mimeMessageHelper.setFrom(from);
//            mimeMessageHelper.setTo(pessoaDTO.getEmail());
//            mimeMessageHelper.setSubject(assunto);
//            mimeMessageHelper.setText(geContentFromTemplate(pessoaDTO, templateName), true);
//            emailSender.send(mimeMessageHelper.getMimeMessage());
//        } catch (MessagingException | IOException | TemplateException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String geContentFromTemplate(PessoaDTO pessoaDTO, String templateName) throws IOException, TemplateException {
//        Map<String, Object> dados = new HashMap<>();
//        dados.put("nome", pessoaDTO.getNome());
//        dados.put("id", pessoaDTO.getIdPessoa());
//        dados.put("suporte_email", from);
//        fmConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
//        Template template = fmConfiguration.getTemplate(templateName);
//        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
//        return html;
//    }
}
