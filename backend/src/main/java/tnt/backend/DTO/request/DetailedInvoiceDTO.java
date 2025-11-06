package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.DetailedInvoice;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailedInvoiceDTO {
    @NotEmpty(message = "Invoice can't be empty")
    @Min(value = 0, message = "Invalid invoice id!")
    private int idInvoice;

    @NotEmpty(message = "Ticket can't be empty")
    @Min(value = 0, message = "Invalid ticket id!")
    private int idTicket;

    @Min(value = 0, message = "Invalid quantity")
    private double quantity;


    @NotEmpty(message = "Creator can't be empty")
    @Min(value = 0,message = "Invalid id creator!")
    private int idCreator;

    @Min(value = 0,message = "Invalide id editor")
    private int idEditor;

    private boolean status;

    public DetailedInvoiceDTO(DetailedInvoice detailedInvoice) {
        this.idInvoice = detailedInvoice.getInvoice().getId();
        this.idTicket = detailedInvoice.getTicket().getId();
        this.quantity = detailedInvoice.getQuantity();
        this.idCreator = detailedInvoice.getCreator().getId();
        this.idEditor = detailedInvoice.getEditor().getId();
        this.status = detailedInvoice.isStatus();

    }
}
