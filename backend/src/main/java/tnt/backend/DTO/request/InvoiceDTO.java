package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Customer;
import tnt.backend.Entity.Employee;
import tnt.backend.Entity.Invoice;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    @NotEmpty(message = "Food code can't be empty")
    @Size(min = 1, max = 50, message = "Food code exceed the allowed number of characters")
    private  String invoiceCode;

    @NotEmpty(message = "Employee can't be empty")
    @Min(value = 0, message = "Invalid employee id!")
    private int idEmployee;

    @NotEmpty(message = "Customer can't be empty")
    @Min(value = 0, message = "Invalid customer id!")
    private int idCustomer;

    @Min(value = 0, message = "Invalid total price")
    private double totalPrice;

    @NotEmpty(message = "Payment type can't be empty")
    @Size(min = 1, max = 255, message = "Payment type exceed the allowed number of characters")
    private  String paymentType;

    @NotEmpty(message = "QR code can't be empty")
    @Size(min = 1, max = 255, message = "QR code exceed the allowed number of characters")
    private  String qrcode;

    @NotEmpty(message = "Creator can't be empty")
    @Min(value = 0, message = "Invalid creator id!")
    private int idCreator;

    @Min(value = 0, message = "Invalid creator id!")
    private int idEditor;

    private boolean status;

    public InvoiceDTO(Invoice invoice) {
        this.invoiceCode = invoice.getInvoiceCode();
        this.idEmployee = invoice.getEmployee().getId();
        this.idCustomer = invoice.getCustomer().getId();
        this.totalPrice = invoice.getTotalPrice();
        this.paymentType = invoice.getPaymentType();
        this.qrcode = invoice.getQrcode();
        this.idCreator = invoice.getCreator().getId();
        this.idEditor =  invoice.getEditor()!=null?invoice.getEditor().getId():0;
        this.status = invoice.isStatus();
    }
}
