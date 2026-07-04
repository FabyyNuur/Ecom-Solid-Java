package ecom.ioc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ecom.discount.IDiscountStrategy;
import ecom.repositories.IOrderRepository;
import ecom.services.IInvoiceService;
import ecom.services.IOrderCompletionService;
import ecom.services.IPricingService;
import ecom.services.IStockService;
import ecom.services.OrderCompletionService;
import ecom.services.OrderManager;
import ecom.services.notification.CompositeEmailSender;
import ecom.services.notification.IEmailSender;

public class ApplicationContext {
    private static final String CONFIG_DIR = "ecom/config/";

    private final IoCContainer container = new IoCContainer();
    private Map<String, String> configDiscount;
    private List<String> discountLabels;

    public void demarrer() throws IOException {
        Map<String, String> configServices = ConfigLoader.lireFichier(CONFIG_DIR + "services.yml");
        configDiscount = ConfigLoader.lireFichier(CONFIG_DIR + "discount.yml");
        discountLabels = new ArrayList<>(configDiscount.keySet());

        IOrderRepository orderRepository = instancierService(configServices, "IOrderRepository");
        container.register(IOrderRepository.class, orderRepository);

        IPricingService pricingService = instancierService(configServices, "IPricingService");
        container.register(IPricingService.class, pricingService);

        IInvoiceService invoiceService = instancierService(configServices, "IInvoiceService");
        container.register(IInvoiceService.class, invoiceService);

        IStockService stockService = instancierService(configServices, "IStockService");
        container.register(IStockService.class, stockService);

        IEmailSender emailSender = creerEmailSender();
        container.register(IEmailSender.class, emailSender);

        IOrderCompletionService orderCompletionService = new OrderCompletionService(
                invoiceService, emailSender, orderRepository);
        container.register(IOrderCompletionService.class, orderCompletionService);

        OrderManager orderManager = new OrderManager(stockService, pricingService, orderCompletionService);
        container.register(OrderManager.class, orderManager);
    }

    public <T> T get(Class<T> type) {
        return container.get(type);
    }

    public List<String> getDiscountLabels() {
        return discountLabels;
    }

    public IDiscountStrategy getDiscount(String type) {
        String nomClasse = configDiscount.get(type);
        if (nomClasse == null) {
            throw new IllegalArgumentException("Type de reduction inconnu : " + type);
        }
        return ConfigLoader.instancier(nomClasse);
    }

    private <T> T instancierService(Map<String, String> config, String cle) {
        String nomClasse = config.get(cle);
        if (nomClasse == null) {
            throw new IllegalStateException(cle + " manquant dans ecom/config/services.yml");
        }
        return ConfigLoader.instancier(nomClasse);
    }

    private IEmailSender creerEmailSender() throws IOException {
        List<String> classes = ConfigLoader.lireListe(CONFIG_DIR + "notification.yml", "IEmailSender");
        if (classes.isEmpty()) {
            throw new IllegalStateException("IEmailSender manquant dans ecom/config/notification.yml");
        }

        List<IEmailSender> senders = new ArrayList<>();
        for (String nomClasse : classes) {
            senders.add(ConfigLoader.instancier(nomClasse));
        }

        if (senders.size() == 1) {
            return senders.get(0);
        }
        return new CompositeEmailSender(senders);
    }
}
