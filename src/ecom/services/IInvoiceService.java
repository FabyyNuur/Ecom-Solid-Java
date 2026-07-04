package ecom.services;

public interface IInvoiceService {
    void generatePdfInvoice(String email, float total);
}
