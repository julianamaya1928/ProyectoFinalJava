package uniquindio.edu.co.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EnvioEmail {
    private String destinatario, asunto, mensaje;

    


    public EnvioEmail(String destinatario, String asunto, String mensaje) {
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }


    private Session crearSesion() {


        // Se definen las credenciales de la cuenta de correo
        final String username = "juliand.amayag@uqvirtual.edu.co";
        final String password = "nasy hpoc ashq yjao";


        // Se configuran las propiedades de la conexión
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        // Se crea un objeto de tipo Authenticator
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };


        // Se crea la sesión
        return Session.getInstance(props, authenticator);


    }


    public void enviarNotificacion() {


        Session session = crearSesion();


        try {
            // Se crea un objeto de tipo Message
            Message message = new MimeMessage(session);


            // Se configura el remitente
            message.setFrom(new InternetAddress( "no-reply@email.com" ));


            // Se configura el destinatario
            message.setRecipients( Message.RecipientType.TO, InternetAddress.parse(destinatario));


            // Se configura el asunto del mensaje
            message.setSubject( asunto );


            // Se configura el mensaje a enviar
            message.setText( mensaje );


            // Se envía el mensaje
            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
