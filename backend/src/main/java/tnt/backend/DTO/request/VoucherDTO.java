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
import tnt.backend.Entity.Employee;
import tnt.backend.Entity.Voucher;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VoucherDTO {

    @Size(min = 1, max = 50, message = "Invalid voucher code")
    private String voucherCode;

    @NotEmpty(message = "Voucher name can't empty")
    @Size(min =0, max = 50, message = "Voucher name exceed the allowed number of characters")
    private String voucherName;

    @Min(value =0, message = "Invalid core value")
    private int coreValue;

    @Min(value = 0, message = "Invalid quantity")
    private  int quantity;

    @Min(value = 0, message = "Invalid quantity used")
    private int quantityUsed;

    private  boolean voucherType;

    @Min(value = 0, message = "Invalid creator id!")
    private int idCreator;

    @Min(value = 0, message = "Invalid creator id!")
    private int idEditor;

    private boolean status;

    public VoucherDTO(Voucher voucher) {
        this.voucherCode = voucher.getVoucherCode();
        this.quantity = voucher.getQuantity();
        this.coreValue = voucher.getCoreValue();
        this.voucherName = voucher.getVoucherName();
        this.quantityUsed = voucher.getQuantityUsed();
        this.voucherType = voucher.isVoucherType();
        this.idCreator = voucher.getCreator().getId();
        this.idEditor = voucher.getEditor()!=null?voucher.getEditor().getId():0;
        this.status = voucher.isStatus();
    }
}
