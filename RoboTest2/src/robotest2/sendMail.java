/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robotest2;

/**
 *
 * @author kyh471
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class sendMail {

    public static void main(String[] args) {
    }

    public static void Send(String email) {
        // This code taken from: http://www.java-samples.com/showtutorial.php?tutorialid=676

        // Collect the necessary information to send a simple message
        // Make sure to replace the values for host, to, and from with
        // valid information.
        // host - must be a valid smtp server that you currently have
        // access to.
        // to - whoever is going to get your email
        // from - whoever you want to be. Just remember that many smtp
        // servers will validate the domain of the from address
        // before allowing the mail to be sent.
        String host = "RoboAdvisor.com";
        String to = email;
        String from = "RoboAdvisor";
        String subject = "Your RoboAdvisor Password";
        String messageText = "I am sending a message using the"
                + " JavaMail API.\n"
                + Student.currentStudent.getPassword();
        boolean sessionDebug = false;
        // Create some properties and get the default Session.
        Properties props = System.getProperties();
        props.put("mail.host", host);
        props.put("mail.transport.protocol", "smtp");
        Session session = Session.getDefaultInstance(props, null);
        // Set debug on the Session so we can see what is going on
        // Passing false will not echo debug info, and passing true
        // will.
        session.setDebug(sessionDebug);
        try {
            // Instantiate a new MimeMessage and fill it with the
            // required information.
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(messageText);
            // Hand the message to the default transport service
            // for delivery.
            Transport.send(msg);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
