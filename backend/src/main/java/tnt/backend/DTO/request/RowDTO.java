package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Employee;
import tnt.backend.Entity.Row;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RowDTO {
    @NotEmpty(message = "Room can't be empty")
    @Min(value = 0, message = "Invalid Room id!")
    private int idRoom;

    @NotEmpty(message = "Row name can't be empty")
    @Size(min=0, max = 255, message = "Row name exceed the allowed number of characters")
    private String rowName;

    @NotEmpty(message = "Creator can't be empty")
    @Min(value = 0, message = "Invalid creator id!")
    private int idCreator;

    @Min(value = 0, message = "Invalid creator id!")
    private int idEditor;

    private boolean status;

    public RowDTO(Row row){
        this.idRoom = row.getId();
        this.rowName = row.getRowName();
        this.idCreator = row.getCreator().getId();
        this.idEditor = row.getEditor().getId();
        this.status = row.isStatus();
    }
}
