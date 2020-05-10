package Servlets;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	public static boolean send(String fname, String lname, String email, String phone, String summary) {
		// Recipient's email ID needs to be mentioned.
        String to = "richard.gandolfo@puretouchcleaningservices.com";

        // Sender's email ID needs to be mentioned
        String from = "ContactPTC@puretouchcleaningservices.com";

        // Assuming you are sending email from through gmails smtp
        //String host = "smtp.gmail.com";
        String host = "mail.puretouchcleaningservices.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        
        try {
        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("ContactPTC@puretouchcleaningservices.com", "edge268843562");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("andrew.gandolfo@puretouchcleaningservices.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("gandolforichard@gmail.com"));


            // Set Subject: header field
            message.setSubject("New Customer: " + fname+" "+lname);

            /*String htmlbody="<table>"
            		+ "<tr><th>Name</th><th>Email</th><th>Phone</th></tr>"+
            		"<tr><td>"+name+"</td><td>"+email+"</td><td>"+phone+"</td></tr>"
            		+ "</table><br><b>Summary</b>"+summary; */
            String htmlbody = "<b>Name: </b>"+fname+" "+lname+ "<br>"+
            				  "<b>Email: </b>"+email+ "<br>"+
            				  "<b>Phone: </b>"+phone+ "<br>"+
            				  "<b>Summary: </b>"+summary;
            
            message.setContent(htmlbody, "text/html");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            return true;
        } catch (Exception mex) {
            mex.printStackTrace();
            return false;
        }


	}

}
