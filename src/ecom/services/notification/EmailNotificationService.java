package ecom.services.notification;

public class EmailNotificationService implements IEmailSender {
    @Override
    public void sendEmail(String email, String message) {
        System.out.println("Email envoyé à " + email + " : " + message);
    }
}
