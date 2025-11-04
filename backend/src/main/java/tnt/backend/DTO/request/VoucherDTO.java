package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Employee;
import tnt.backend.Entity.Voucher;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VoucherDTO {
    @Min(value = 0, message = "Invalid quantity")
    private  int quantity;

    private  boolean voucherType;

    @NotEmpty(message = "Creator can't be empty")
    @JsonFormat(shape =  JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate modifiedDate;

    @NotEmpty(message = "Creator can't be empty")
    @Min(value = 0, message = "Invalid creator id!")
    private int idCreator;

    @Min(value = 0, message = "Invalid creator id!")
    private int idEditor;

    private boolean status;

    public VoucherDTO(Voucher voucher) {
        this.quantity = voucher.getQuantity();
        this.voucherType = voucher.isVoucherType();
        this.idCreator = voucher.getId();
        this.idEditor = voucher.getEditor().getId();
        this.createdDate = voucher.getCreatedDate();
        this.modifiedDate = voucher.getModifiedDate();
        this.status = voucher.isStatus();
    }
}
