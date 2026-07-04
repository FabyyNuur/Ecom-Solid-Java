package ecom.services.notification;

public interface IEmailSender {
    void sendEmail(String email, String message);
}
