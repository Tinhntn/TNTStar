package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.DetailedFood;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailedFoodDTO {
    @NotEmpty(message = "Invoice can't be empty")
    @Min(value = 0, message = "Invalid invoice id!")
    private int idInvoice;

    @NotEmpty(message = "Food can't be empty")
    @Min(value = 0, message = "Invalid food id!")
    private int idFood;

    @Min(value = 0, message = "Invalid price")
    private double price;

    @Min(value = 0, message = "Invalid quantity")
    private int quantity;

    @NotNull(message = " Create date can't be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate modifiedDate;

    @NotEmpty(message = "Creator can't be empty")
    @Min(value = 0,message = "Invalid id creator!")
    private int idCreator;

    @Min(value = 0,message = "Invalide id editor")
    private int idEditor;

    private boolean status;

    public DetailedFoodDTO(DetailedFood detailedFood) {
        this.idInvoice = detailedFood.getInvoice().getId();
        this.idFood = detailedFood.getFood().getId();
        this.price = detailedFood.getFood().getPrice();
        this.quantity = detailedFood.getQuantity();
        this.createdDate = detailedFood.getCreatedDate();
        this.modifiedDate = detailedFood.getModifiedDate();
        this.idCreator = detailedFood.getCreator().getId();
        this.idEditor = detailedFood.getEditor().getId();
        this.status = detailedFood.isStatus();
    }
}
