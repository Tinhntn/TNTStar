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
import tnt.backend.Entity.Food;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {
    @NotEmpty(message = "Food code can't be empty")
    @Size(min = 1, max = 255, message = "Food code exceed the allowed number of characters")
    private  String foodCode;

    @NotEmpty(message = "Food name can't be empty")
    @Size(min = 1, max = 255, message = "Food name exceed the allowed number of characters")
    private  String foodName;

    @Min(value = 0, message = "Invalid price")
    private double price;

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

    public FoodDTO(Food food) {
        this.foodCode = food.getFoodCode();
        this.foodName = food.getFoodName();
        this.price = food.getPrice();
        this.createdDate = food.getCreatedDate();
        this.modifiedDate = food.getModifiedDate();
        this.idCreator = food.getCreator().getId();
        this.idEditor = food.getEditor().getId();
        this.status = food.isStatus();
    }
}
