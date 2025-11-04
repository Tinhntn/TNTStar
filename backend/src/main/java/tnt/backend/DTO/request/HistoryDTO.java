package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Customer;
import tnt.backend.Entity.History;
import tnt.backend.Entity.Invoice;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDTO {

    @NotNull(message = "Invoice id can't be empty")
    @Min(value = 0, message = "Invalid invoice id")
    private int idInvoice;

    @NotNull(message = "Customer id can't be empty")
    @Min(value = 0, message = "Invalid customer id")
    private int idCustomer;

    @NotEmpty(message = "Creator can't be empty")
    @JsonFormat(shape =  JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate modifiedDate;

    private boolean status;

    public HistoryDTO(History history){
        this.idInvoice =  history.getInvoice().getId();
        this.idCustomer = history.getCustomer().getId();
        this.createdDate = history.getCreatedDate();
        this.modifiedDate = history.getModifiedDate();
        this.status = history.isStatus();
    }
}
