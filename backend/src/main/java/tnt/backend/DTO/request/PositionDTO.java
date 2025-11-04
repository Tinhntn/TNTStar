package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Position;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {
    @NotEmpty(message = "Position code can't be empty")
    @Size(min = 1, max = 50, message = "Position code exceed the allowed number of characters")
    private String positionCode;

    @NotEmpty(message = "Position name can't be empty")
    @Size(min=0, max = 255, message = "Position name exceed the allowed number of characters")
    private String positionName;

    @NotNull(message = " Create date can't be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate modifiedDate;

    private boolean status;

    public PositionDTO(Position position){
        this.positionCode = position.getPositionCode();
        this.positionName = position.getPositionName();
        this.createdDate = position.getCreatedDate();
        this.modifiedDate = position.getModifiedDate();
        this.status = position.isStatus();
    }
}
