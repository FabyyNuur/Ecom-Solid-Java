package services;

public class NotificationService {
    // toujours OCP probleme de violation 
    public void sendEmail(String email, String message) {
        System.out.println("Email envoyé à " + email + " : " + message);
    }
}
