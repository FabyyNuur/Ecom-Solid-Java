package ecom.services.notification;

import java.util.List;

public class CompositeEmailSender implements IEmailSender {
    private final List<IEmailSender> senders;

    public CompositeEmailSender(List<IEmailSender> senders) {
        this.senders = senders;
    }

    @Override
    public void sendEmail(String email, String message) {
        for (IEmailSender sender : senders) {
            sender.sendEmail(email, message);
        }
    }
}
